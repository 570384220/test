package cn.zch.ssm.service;

import java.util.List;

import cn.zch.ssm.po.Items;

public interface ItemsService {

	List<Items> findAll();

	Items queryItemsByid(Integer id);

	void save(Items items);

}
