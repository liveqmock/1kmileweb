package com.yeahwell.epu.oms.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahwell.epu.admin.form.MasterStationForm;
import com.yeahwell.epu.common.util.BeanUtil;
import com.yeahwell.epu.oms.model.MasterStation;
import com.yeahwell.epu.service.oms.MasterStationService;

@Service
public class MasterStationBusiness {

	@Autowired
	private MasterStationService masterStationService;
	
	public List<MasterStation> findAllMasterStation(){
		List<MasterStation> masterStationList = new ArrayList<MasterStation>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Map<String, Object>> masterStationMapList = masterStationService.findAllMasterStation(paramMap);
		MasterStation masterStation = null;
		for(Map<String, Object> masterStationMap : masterStationMapList){
			masterStation = new MasterStation();
			BeanUtil.transMap2Bean(masterStationMap, masterStation);
			masterStationList.add(masterStation);
		}
		return masterStationList;
	}
	
	public MasterStation findMasterStationByStructId(Integer structId){
		MasterStation masterStation = new MasterStation();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("structId", structId);
		Map<String, Object> masterStationMap = masterStationService.findMasterStationByStructId(paramMap);
		if(null != masterStationMap){
			BeanUtil.transMap2Bean(masterStationMap, masterStation);
		}
		return masterStation;
	}
	
	public MasterStation findMasterStationById(Integer stationId){
		MasterStation masterStation = new MasterStation();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("stationId", stationId);
		Map<String, Object> masterStationMap = masterStationService.findMasterStationById(paramMap);
		if(null != masterStationMap){
			BeanUtil.transMap2Bean(masterStationMap, masterStation);
		}
		return masterStation;
	}
	
	public boolean addMasterStation(MasterStationForm masterStationForm){
		Map<String, Object> paramMap = BeanUtil.transBean2Map(masterStationForm);
		return masterStationService.addMasterStation(paramMap);
	}
	
	public boolean updateMasterStation(MasterStationForm masterStationForm){
		Map<String, Object> paramMap = BeanUtil.transBean2Map(masterStationForm);
		return masterStationService.updateMasterStation(paramMap);
	}
	
	public boolean updateStatus(List<Integer> stationIdList){
		return false;
	}
	
}
