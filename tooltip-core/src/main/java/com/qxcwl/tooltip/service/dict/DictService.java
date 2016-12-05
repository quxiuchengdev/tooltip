package com.qxcwl.tooltip.service.dict;

import com.qxcwl.tooltip.service.CrudService;
import com.qxcwl.tooltip.dao.dict.DictDao;
import com.qxcwl.tooltip.model.dict.Dict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DictService extends CrudService<DictDao, Dict> {
	
}
