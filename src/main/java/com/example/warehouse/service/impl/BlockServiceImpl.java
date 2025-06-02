package com.example.warehouse.service.impl;

import com.example.warehouse.dto.mapper.BlockMapper;
import com.example.warehouse.dto.request.BlockRequest;
import com.example.warehouse.dto.response.BlockResponse;
import com.example.warehouse.entity.Block;
import com.example.warehouse.entity.RackedBlock;
import com.example.warehouse.entity.Room;
import com.example.warehouse.entity.UnRackedBlock;
import com.example.warehouse.exceptions.RoomNotFoundByIdException;
import com.example.warehouse.exceptions.UnSupportedBlockTypeException;
import com.example.warehouse.repository.BlockRepository;
import com.example.warehouse.repository.RoomRepository;
import com.example.warehouse.service.contract.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private BlockMapper blockMapper;

    @Transactional
    @Override
    public BlockResponse createBlock(BlockRequest request, String userId) {
        Room room = roomRepository.findById(userId).orElseThrow(() -> new RoomNotFoundByIdException("Room is Not Exist!!"));
        Block block = switch (request.type()) {
            case RACKED -> blockMapper.toEntity(request, new RackedBlock());
            case UNRACKED -> blockMapper.toEntity(request, new UnRackedBlock());
            default -> throw new UnSupportedBlockTypeException(request.type() + " is Not Available!!");
        };
        block.setRoom(room);
        roomRepository.save(room);
        blockRepository.save(block);
        return blockMapper.toResponse(block);
    }
}
