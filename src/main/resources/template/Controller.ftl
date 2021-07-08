package ${content.controller.classPackage};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import ${content.service.classPackage}.${content.service.className};
import ${content.entity.classPackage}.${content.entity.className};
import ${content.basePackage}.utils.HttpJsonResult;
import ${content.basePackage}.utils.Page;
import javax.validation.Valid;

/**
 * 何冠勋
 */
@Api(value = "${content.table.tableComment}",tags = "${content.table.tableComment}")
@RequestMapping(value = "/${content.entity.className?uncap_first}")
@RestController
public class ${content.controller.className}{

	@Autowired
	private ${content.service.className} ${content.service.className?uncap_first};

	@ApiOperation("分页查询")
	@GetMapping(value = "/getPageList")
	public HttpJsonResult<Page<${content.entity.className}>> getPageList(${content.entity.className} ${content.entity.className?uncap_first}) {
		return HttpJsonResult.ok(${content.service.className?uncap_first}.getPageList(${content.entity.className?uncap_first}));
	}

	@ApiOperation("获取全部信息")
	@GetMapping(value = "/list")
	public HttpJsonResult<List<${content.entity.className}>> getList(${content.entity.className} ${content.entity.className?uncap_first}){
		 return HttpJsonResult.ok(${content.service.className?uncap_first}.getList(${content.entity.className?uncap_first}));
	}

	<#if content.entity.primaryKeyAttr??>
	@ApiOperation("根据id获取${content.entity.className?uncap_first}信息")
	@GetMapping(value = "/getById")
	public HttpJsonResult<${content.entity.className}> getById(Integer id){
		return HttpJsonResult.ok(${content.service.className?uncap_first}.get${content.entity.className}ById(id));
	}

	@ApiOperation("新增或者修改")
	@PostMapping(value = "/saveOrUpdate")
	public HttpJsonResult<String> saveOrUpdate(@Valid ${content.entity.className} ${content.entity.className?uncap_first}){
		if(${content.entity.className?uncap_first}.getId()!=null){
			${content.service.className?uncap_first}.${content.service.item.updateNotNull.value!}(${content.entity.className?uncap_first});
			return HttpJsonResult.ok();
		}
		${content.service.className?uncap_first}.${content.service.item.insertNotNull.value!}(${content.entity.className?uncap_first});
		return HttpJsonResult.ok();
	}

	@ApiOperation("删除")
	@GetMapping(value = "/deleteById")
	public HttpJsonResult<String> deleteById(Integer id){
		${content.service.className?uncap_first}.${content.service.item.deleteById.value!}(id);
		return HttpJsonResult.ok();
	}
	</#if>
}
