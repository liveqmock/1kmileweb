package com.yeahwell.epu.service.express;

import java.util.List;
import java.util.Map;

import com.yeahwell.common.pagenation.Page;

/**
 * 物流服务——代收货
 * 功能描述：提供包裹的入站、出站
 * @author yeahwell19920525@gmail.com
 * @created 2014-2-9 下午7:11:56
 * @version 1.0.0
 * @date 2014-2-9 下午7:11:56
 */
public interface ExpressService {
    
    /**
     * 功能描述：新增包裹信息
     * @param paramMap 新增时所需的必备信息
     * @return boolean 新增包裹成功返回true,失败则返回flase
     */
    public boolean addPackage(Map<String,Object> paramMap);

    /**
     * 功能描述：包裹入站
     * @param paramMap 运单号expressNo
     * @return boolean 包裹入站成功返回true,失败则返回flase
     */
    public boolean updatePackageInStation(Map<String,Object> paramMap);
    
    /**
     * 功能描述：顾客提货
     * @param paramMap 运单号expressNo,提货码pickupCode
     * @return boolean 顾客提货成功返回true,失败则返回flase
     */
    public boolean updatePackageOutStation(Map<String,Object> paramMap);
    
    public boolean updatePackageEmailIncr(Map<String,Object> paramMap);
    
    public boolean updatePackageMobileIncr(Map<String,Object> paramMap);
    
    public boolean updatePackageStatus(Map<String,Object> paramMap);
    
    public void updatePackageAutoClose(Map<String,Object> paramMap);
    
    /**
     * 功能描述：根据包裹编码查询包裹详细信息(除提货码以外的所有信息)
     * @param paramMap 包裹编码packageId
     * @return Map<String, Object> 返回包裹详细信息
     */
    public Map<String, Object> findPackageById(Map<String,Object> paramMap);
    
    public Map<String, Object> findPackagePickupCode(Map<String, Object> paramMap);
    
    /**
     * 功能描述：根据运单号查看包裹详细信息
     * @param paramMap 运单号expressNo,站点编码stationId
     * @return Map<String, Object> 返回包裹详细信息
     */
    public List<Map<String, Object>> findPackageByExpressNo(Map<String,Object> paramMap);

    public List<Map<String, Object>> findPackageNotice(Map<String,Object> paramMap);
    
    public Integer findPackageCount(Map<String, Object> paramMap);
    
    /**
     * 功能描述：根据站点编码和包裹状态分页查询包裹信息
     * @param paramMap :
     * @return 
     */
    public Page findPackagePage(Map<String,Object> paramMap);
    
    public List<Map<String, Object>> findPackageAll(Map<String,Object> paramMap);
    
}
