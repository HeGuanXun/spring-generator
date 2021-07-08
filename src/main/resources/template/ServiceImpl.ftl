package ${content.serviceImpl.classPackage};
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${content.service.classPackage}.${content.service.className};
import ${content.dao.classPackage}.${content.dao.className};
import ${content.basePackage}.utils.${content.assist.className};
import ${content.entity.classPackage}.${content.entity.className};
import ${content.basePackage}.utils.Page;
/**
 * ${content.entity.className}的服务接口的实现类
 * @author
 */
@Service
public class ${content.serviceImpl.className} implements ${content.service.className}{

	private final Logger LOG = LogManager.getLogger(this.getClass());

	@Autowired
	private ${content.dao.className} ${content.dao.className?uncap_first};

	@Override
	public Page<${content.entity.className}> getPageList(${content.entity.className} ${content.entity.className?uncap_first}){
		Assist assist = new Assist(${content.entity.className?uncap_first}.getCurrentPage(),${content.entity.className?uncap_first}.getPageSize());
		buildParameter(${content.entity.className?uncap_first},assist);
		Page<${content.entity.className}> page = new Page<>();
		page.setCurrentPage(${content.entity.className?uncap_first}.getCurrentPage());
		page.setPageSize(${content.entity.className?uncap_first}.getPageSize());
		page.setItems(${content.dao.className?uncap_first}.${content.dao.item.select.value!}(assist));
		page.setTotal(${content.dao.className?uncap_first}.${content.dao.item.count.value!}(assist));
		return page;
	}

	@Override
	public List<${content.entity.className}> getList(${content.entity.className} ${content.entity.className?uncap_first}){
		Assist assist = new Assist();
		buildParameter(${content.entity.className?uncap_first},assist);
		return ${content.dao.className?uncap_first}.${content.dao.item.select.value!}(assist);
	}
	<#if content.entity.primaryKeyAttr??>
	@Override
	public ${content.entity.className} get${content.entity.className}ById(${content.entity.primaryKeyAttr.javaType} id){
		return ${content.dao.className?uncap_first}.${content.dao.item.selectById.value!}(id);
	}
	</#if>

	@Override
	public String ${content.service.item.insertNotNull.value!}(${content.entity.className} ${content.entity.className?uncap_first}){
		if (${content.entity.className?uncap_first} == null){
			return ${content.assist.className}.resultFormat(${content.assist.className}.C500, "${content.entity.className?uncap_first}不能为null");
		}
		<#if content.entity.cantNullAttrs?exists>
		if(<#list content.entity.cantNullAttrs as item>${content.entity.className?uncap_first}.${item.fget}() == null <#if item?has_next>||</#if> </#list>){
			return ${content.assist.className}.resultFormat( ${content.assist.className}.C500, null);
		}
		</#if>
		int result = ${content.dao.className?uncap_first}.${content.dao.item.insertNotNull.value!}(${content.entity.className?uncap_first});
		return ${content.assist.className}.resultFormat(${content.assist.className}.C200, result);
	}

	<#if content.entity.primaryKeyAttr??>
	@Override
	public String ${content.service.item.updateNotNull.value!}(${content.entity.className} ${content.entity.className?uncap_first}){
		if (${content.entity.className?uncap_first} == null) {
			return ${content.assist.className}.resultFormat( ${content.assist.className}.C500, null);
		}
		int result = ${content.dao.className?uncap_first}.${content.dao.item.updateNotNullById.value!}(${content.entity.className?uncap_first});
		return ${content.assist.className}.resultFormat(${content.assist.className}.C200, result);
	}

	@Override
	public String ${content.service.item.deleteById.value!}(${content.entity.primaryKeyAttr.javaType} id){
		if (id == null) {
			return ${content.assist.className}.resultFormat(${content.assist.className}.C500, null);
		}
		int result = ${content.dao.className?uncap_first}.${content.dao.item.deleteById.value!}(id);
		return ${content.assist.className}.resultFormat(${content.assist.className}.C200, result);
	}
	</#if>
	void buildParameter(${content.entity.className} ${content.entity.className?uncap_first},Assist assist){
	<#list content.entity.attrs as item>
	if (${content.entity.className?uncap_first}.get${item.field?cap_first}() != null){
		assist.setRequires(Assist.whereRequire("${item.field}",${content.entity.className?uncap_first}.get${item.field?cap_first}()));
	}
	</#list>
}

}