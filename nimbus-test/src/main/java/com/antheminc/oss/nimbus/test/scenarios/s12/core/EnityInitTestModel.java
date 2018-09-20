/**
 * 
 */
package com.antheminc.oss.nimbus.test.scenarios.s12.core;

import com.antheminc.oss.nimbus.domain.defn.Domain;
import com.antheminc.oss.nimbus.domain.defn.Domain.ListenerType;
import com.antheminc.oss.nimbus.domain.defn.Execution.Config;
import com.antheminc.oss.nimbus.domain.defn.Model;
import com.antheminc.oss.nimbus.domain.defn.Repo;
import com.antheminc.oss.nimbus.domain.defn.Repo.Database;
import com.antheminc.oss.nimbus.domain.defn.extension.ConfigConditional;
import com.antheminc.oss.nimbus.domain.defn.extension.Script;
import com.antheminc.oss.nimbus.domain.defn.extension.Script.Type;
import com.antheminc.oss.nimbus.entity.AbstractEntity.IdLong;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Jayant Chaudhuri
 *
 */
@Domain(value="entityinittest", includeListeners={ListenerType.persistence})
@Repo(Database.rep_mongodb)
@Getter @Setter
public class EnityInitTestModel extends IdLong{
	private static final long serialVersionUID = 1L;
	
	@Script(type=Type.SPEL_INLINE, value="findParamByPath('/parameter2').setState('Value2')")
	private Action1 inline_para;
	
	@Script(type=Type.SPEL_FILE, value="/scripts/entity_init_test.txt")
	private Action1 file_para;
	
	@Script(type=Type.GROOVY, value="/scripts/entity_init_test.txt")
	private Action1 groovy_para;
	
	@Getter
	@Setter
	@Model
	public static class Action1{
		@ConfigConditional(
				config= {@Config(url="/parameter3/_process?fn=_set&value=Value3")}
		)
		private String parameter2;
		
		private String parameter3;
	}
	
}
