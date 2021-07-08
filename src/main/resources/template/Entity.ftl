package ${content.entity.classPackage};
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;

/**
 * ${content.entity.tableName}实体类
 * @author
 */
@ApiModel(value="${content.entity.tableName}实体类")
@Data
public class ${content.entity.className} extends BaseEntity{
<#list content.entity.attrs as item>
	<#if item.field != "id">
	@ApiModelProperty("${item.remarks!}")
	</#if>
	<#if item.javaType == "java.util.Date" >
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	</#if>
	<#if item.nullable == false && item.field != "id">
	@NotNull(message = "${item.remarks!}不能为空")
	</#if>
	private ${item.javaType} ${item.field};

</#list>
}
