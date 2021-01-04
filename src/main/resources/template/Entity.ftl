package ${content.entity.classPackage};
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * ${content.entity.tableName}实体类
 * @author
 */
@ApiModel(value="${content.entity.tableName}实体类")
@Setter
@Getter
public class ${content.entity.className} {

	<#list content.entity.attrs as item>
	<#if item.field != "id" >
	@ApiModelProperty("${item.remarks!}")
	</#if>
	<#if item.field == "createTime" >
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	</#if>
	private ${item.javaType} ${item.field};

	</#list>
	@Override
	public String toString() {
		return "${content.entity.className!} [<#list content.entity.attrs as item>${item.field}=" + ${item.field} + " <#if item?has_next>,</#if> </#list>]";
	}
}
