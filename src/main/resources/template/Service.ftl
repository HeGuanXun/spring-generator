package ${content.service.classPackage};
import java.util.List;
import ${content.entity.classPackage}.${content.entity.className};

/**
 * ${content.entity.className}的服务接口
 * @author
 */
public interface ${content.service.className}{
	/**
	 * 获得所以数据集
	 */
	List<${content.entity.className}> getList(${content.entity.className} ${content.entity.className?uncap_first});

	<#if content.entity.primaryKeyAttr??>
	/**
	 * 获取${content.entity.className}信息
	 */
	${content.entity.className} get${content.entity.className}ById(${content.entity.primaryKeyAttr.javaType} id);
	</#if>

	/**
	 * 新增不为null的数据
	 */
	String ${content.service.item.insertNotNull.value!}(${content.entity.className} ${content.entity.className?uncap_first});

	<#if content.entity.primaryKeyAttr??>
	/**
	 * 更新不为null的数据
	 */
	String ${content.service.item.updateNotNull.value!}(${content.entity.className} ${content.entity.className?uncap_first});

	/**
	 * 删除
	 */
	String ${content.service.item.deleteById.value!}(${content.entity.primaryKeyAttr.javaType} id);
	</#if>
}
