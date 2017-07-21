package cn.zch.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zch.ssm.mapper.ItemsMapper;
import cn.zch.ssm.po.Items;
import cn.zch.ssm.po.ItemsExample;
import cn.zch.ssm.service.ItemsService;

@Service
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	ItemsMapper mapper;
	
	@Override
	public List<Items> findAll() {
		return mapper.selectByExample(new ItemsExample());
	}

	@Override
	public Items queryItemsByid(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public void save(Items items) {
		mapper.updateByPrimaryKey(items);
	}

}
