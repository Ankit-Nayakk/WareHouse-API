package com.example.warehouse.service.impl;

import com.example.warehouse.dto.mapper.RackMapper;
import com.example.warehouse.dto.request.RackRequest;
import com.example.warehouse.dto.response.RackResponse;
import com.example.warehouse.entity.Block;
import com.example.warehouse.entity.Rack;
import com.example.warehouse.entity.RackedBlock;
import com.example.warehouse.exceptions.BlockNotFoundByIdException;
import com.example.warehouse.exceptions.RackNotFoundByIdException;
import com.example.warehouse.exceptions.UnSupportedBlockTypeException;
import com.example.warehouse.repository.BlockRepository;
import com.example.warehouse.repository.RackRepository;
import com.example.warehouse.service.contract.RackService;
import com.example.warehouse.shared.QRCodeGeneratorService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RackServiceImpl implements RackService {

    @Autowired
    private RackRepository rackRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private RackMapper rackMapper;

    @Autowired
    private QRCodeGeneratorService qrCodeGeneratorService;


    @Override
    public RackResponse createRack(RackRequest request, String blockId) {
        Block block = blockRepository.findById(blockId)
                .orElseThrow(() -> new BlockNotFoundByIdException("Block Not Found!!!"));

        Rack rack = rackMapper.toEntity(request, new Rack());
        if (block instanceof RackedBlock rackedBlock) {
            rack.setRackedBlock(rackedBlock);
            blockRepository.save(block);
            rackRepository.save(rack);
            return rackMapper.toResponse(rack);
        } else {
            throw new UnSupportedBlockTypeException("Block Type is Not Supported!!! ");
        }
    }

    @Override
    public byte[] generateRackQRCode(String rackId) {
        Rack rack = rackRepository.findById(rackId).orElseThrow(() -> new RackNotFoundByIdException("Rack Not Found !!!"));

        String roomId = rack.getRackedBlock().getRoom().getRoomId();
        String blockId = rack.getRackedBlock().getBlockId();

        String content = String.format("""
                {
                "roomId" : "%s",
                "blockId" : "%s",
                "rackId" : "%s"
                }
                """, roomId, blockId, rack.getId());

        try {
            return qrCodeGeneratorService.generateQRCode(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }
}
