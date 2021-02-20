package ${content.controller.classPackage};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ${content.service.classPackage}.${content.service.className};
import ${content.entity.classPackage}.${content.entity.className};
import com.hgx.common.utils.HttpJsonResult;
import java.util.List;

/**
 * 何冠勋
 */
@Api(value = "${content.table.tableComment}",tags = "${content.table.tableComment}")
@RequestMapping(value = "/${content.entity.className?uncap_first}")
@RestController
public class ${content.controller.className}{

	@Autowired
	private ${content.service.className} ${content.service.className?uncap_first};
	
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
	public HttpJsonResult<String> saveOrUpdate(${content.entity.className} ${content.entity.className?uncap_first}){
		if(${content.entity.className?uncap_first}.getId()!=null){
			try {
				${content.service.className?uncap_first}.${content.service.item.updateNotNull.value!}(${content.entity.className?uncap_first});
				return HttpJsonResult.ok("更新成功！");
				} catch (Exception e) {
				return HttpJsonResult.errorException("更新失败！"+e);
			}
		}
		try {
			${content.service.className?uncap_first}.${content.service.item.insertNotNull.value!}(${content.entity.className?uncap_first});
		} catch (Exception e) {
			e.printStackTrace();
			return HttpJsonResult.errorException("新增失败！"+e);
		}
		return HttpJsonResult.ok("新增成功！");
	}

	@ApiOperation("删除")
	@GetMapping(value = "/deleteById")
	public HttpJsonResult<String> deleteById(Integer id){
		try {
			${content.service.className?uncap_first}.${content.service.item.deleteById.value!}(id);
		} catch (Exception e) {
			e.printStackTrace();
			return HttpJsonResult.errorException("删除失败！"+e);
		}
		return HttpJsonResult.ok("删除成功！");
	}
	</#if>
}
