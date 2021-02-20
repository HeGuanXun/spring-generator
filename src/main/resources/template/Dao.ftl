package ${content.dao.classPackage};
import java.util.List;
import ${content.assist.classPackage}.${content.assist.className};
import ${content.entity.classPackage}.${content.entity.className};
import org.apache.ibatis.annotations.Param;
/**
 * ${content.entity.className}的Dao接口
 * @author
 */
public interface ${content.dao.className}{

	/**
	 * 获得数据的总行数
	 */
	long ${content.dao.item.count.value!}(Assist assist);
	
	/**
	 * 获得${content.entity.className}数据集合
	 */
	List<${content.entity.className}> ${content.dao.item.select.value!}(Assist assist);
	<#if content.entity.primaryKeyAttr??>
	/**
	 * 通过${content.entity.className}的id获得对象
	 */
	${content.entity.className} ${content.dao.item.selectById.value!}(${content.entity.primaryKeyAttr.javaType} id);
	</#if>
	
	/**
	 * 获得一个${content.entity.className}对象
	 */
	${content.entity.className} ${content.dao.item.selectByObjSingle.value!}(${content.entity.className} obj);
	
	/**
	 * 获得一个${content.entity.className}对象集合
	 */
	List<${content.entity.className}> ${content.dao.item.selectByObj.value!}(${content.entity.className} obj);


	/**
	 * 插入${content.entity.className}中属性值不为null的数据到数据库
	 */
	int ${content.dao.item.insertNotNull.value!}(${content.entity.className} value);
	
	/**
	 * 批量插入,包括null值
	 */
	int ${content.dao.item.insertBatch.value!}(List<${content.entity.className}> value);
	<#if content.entity.primaryKeyAttr??>
	/**
	 * 通过id删除
	 */
	int ${content.dao.item.deleteById.value!}(${content.entity.primaryKeyAttr.javaType} id);
	</#if>

	<#if content.entity.primaryKeyAttr??>
	/**
	 * 通过id更新属性不为null的数据
	 */
	int ${content.dao.item.updateNotNullById.value!}(${content.entity.className} ${content.entity.className?uncap_first});
	</#if>
}