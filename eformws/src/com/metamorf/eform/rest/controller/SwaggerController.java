package com.metamorf.eform.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import io.swagger.models.Model;
import io.swagger.models.Path;
import io.swagger.models.Swagger;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;
import springfox.documentation.swagger2.web.Swagger2Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.metamorf.eform.common.util.StringFunction;

@Controller
@ApiIgnore
@RequestMapping("/swagger")
public class SwaggerController {

	public static final String DOC_URL = "/doc";
	
	private String hostNameOverride;
	@Autowired
	private DocumentationCache documentationCache;
	@Autowired
	private ServiceModelToSwagger2Mapper mapper;
	@Autowired
	private JsonSerializer jsonSerializer;

	@ApiIgnore
	@RequestMapping(value = DOC_URL, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Json> getDocumentation(@RequestParam(value = "group", required = false) String swaggerGroup) {
		String groupName = Optional.fromNullable(swaggerGroup).or(Docket.DEFAULT_GROUP_NAME);
		Documentation documentation = documentationCache.documentationByGroup(groupName);
		if (documentation == null) {
			return new ResponseEntity<Json>(HttpStatus.NOT_FOUND);
		}
		Swagger swagger = mapper.mapDocumentation(documentation);
		swagger.host(hostName());
		return new ResponseEntity<Json>(jsonSerializer.toJson(swagger), HttpStatus.OK);
	}
	
	@ApiIgnore
	@RequestMapping(value = DOC_URL+"/{module}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Json> getDocumentationModule(@PathVariable(value="module") String module) {
		try {
			if(StringFunction.isNotEmpty(module)) {
				Documentation documentation = documentationCache.documentationByGroup(Docket.DEFAULT_GROUP_NAME);
				if (documentation != null) {
					Swagger swag = mapper.mapDocumentation(documentation);
						swag.host(hostName());
					Swagger mSwag = extractModuleFromDoc(swag, module);
					return new ResponseEntity<Json>(jsonSerializer.toJson(mSwag), HttpStatus.OK);
				}
			}
		} catch (Exception e) {}
		return new ResponseEntity<Json>(HttpStatus.NOT_FOUND);
	}
	
	private Swagger extractModuleFromDoc(Swagger swag, String module) {
		ObjectMapper mapper = new ObjectMapper();
		Swagger mSwag = new Swagger();
		mSwag.setInfo(swag.getInfo());
		mSwag.setHost(swag.getHost());
		mSwag.setBasePath(swag.getBasePath());
		Map<String, Path> paths = new LinkedHashMap<>();
		Map<String, Model> defs = new LinkedHashMap<>();
		for(String key : swag.getPaths().keySet()) {
			if(StringFunction.isNotEmpty(key) && key.contains("/"+module)) {
				try {
					Path p = swag.getPaths().get(key);
					paths.put(key, p);
					addDefToListFromPath(defs, swag.getDefinitions(), mapper.writeValueAsString(jsonSerializer.toJson(p)));	
				} catch (Exception e) {}
			}
		}
		mSwag.setPaths(paths);
		mSwag.setDefinitions(defs);
		return mSwag;
	}
	
	private void addDefToListFromPath(Map<String, Model> defs, Map<String, Model> swagDefs, String json) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Pattern p = Pattern.compile("\\\"\\$ref\\\"[^\\\"]*\\\"[^\\\"]*\\\"");
		Matcher m = p.matcher(json);
        while (m.find()) {
        	String def = m.group();
        	def = def.substring(def.lastIndexOf("/")+1, def.length()-1);
        	if(!defs.containsKey(def)) {
        		Model result = extractDefFromDoc(def, swagDefs);
        		defs.put(def, result);
        		addDefToListFromPath(defs, swagDefs, mapper.writeValueAsString(jsonSerializer.toJson(result)));
        	}
        }
	}
	
	private Model extractDefFromDoc(String def, Map<String, Model> swagDefs) {
		if(StringFunction.isNotEmpty(def) && swagDefs != null && swagDefs.keySet() != null) {
			if(swagDefs.containsKey(def) && swagDefs.get(def) != null) {
				return swagDefs.get(def);
			}
		}
		return null;
	}
	
	private String hostName() {
		if ("DEFAULT".equals(hostNameOverride)) {
			URI uri = linkTo(Swagger2Controller.class).toUri();
			String host = uri.getHost();
			int port = uri.getPort();
			if (port > -1) {
				return String.format("%s:%d", host, port);
			}
			return host;
		}
		return hostNameOverride;
	}
}