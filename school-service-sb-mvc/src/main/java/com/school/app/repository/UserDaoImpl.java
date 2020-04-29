package com.school.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.school.app.commons.DBQueries;
import com.school.app.model.PickLIst;
import com.school.app.model.Users;

@Repository
public class UserDaoImpl implements UsersDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Users saveUsersInfo(Users usersInfo) throws Exception {
		try {
			int count =0;
			SqlParameterSource sqlParameterSource = null;
			if(!StringUtils.isEmpty(usersInfo.getId())){
				sqlParameterSource=new MapSqlParameterSource()
				.addValue("name", usersInfo.getName())
				.addValue("age", usersInfo.getAge())
				.addValue("city", usersInfo.getCapital())
				.addValue("state", usersInfo.getState())
				.addValue("id", usersInfo.getId());
				 
				 count= namedParameterJdbcTemplate.update(DBQueries.UPDTE_USERS_BY_ID, sqlParameterSource);
			}else {
				 KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
				sqlParameterSource=new MapSqlParameterSource()
				.addValue("name", usersInfo.getName())
				.addValue("age", usersInfo.getAge())
				.addValue("city", usersInfo.getCapital())
				.addValue("state", usersInfo.getState())
				.addValue("uuid", usersInfo.getUuidCode());
				//count= namedParameterJdbcTemplate.update(DBQueries.INSERT_USERS, sqlParameterSource);
				namedParameterJdbcTemplate.update(DBQueries.INSERT_USERS, sqlParameterSource, generatedKeyHolder, new String[] {"ID"});
				Integer gentedKey =  generatedKeyHolder.getKey().intValue();
				usersInfo.setId(gentedKey);
			}
			Users userInfo = findUsersInfoById(usersInfo.getUuidCode());
			return userInfo;
		} catch (DataAccessException e) {
			throw e;
		}
	}

	@Override
	public List<Users> findAllUsersInfo() throws Exception {
		try {
			List<Users> usersInfos = new ArrayList<>();

			namedParameterJdbcTemplate.query(DBQueries.SELECT_ALL_USERS, rs -> {
				while (rs.next()) {
					Users info = new Users();
					info.setId(rs.getInt("ID"));
					info.setAge(rs.getInt("AGE"));
					info.setName(rs.getString("NAME"));
					info.setCapital(rs.getString("CITY"));
					info.setState(rs.getString("STATE"));
					info.setUuidCode(rs.getString("UUID_CODE"));
					usersInfos.add(info);
				}

				return usersInfos;
			});
			return usersInfos;
		} catch (DataAccessException e) {
			throw e;
		}
	}

	@Override
	public Users findUsersInfoById(String id) throws Exception {
		try {
			SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("id", id);
			Users info = new Users();

			namedParameterJdbcTemplate.query(DBQueries.SELECT_USERS_BY_ID,sqlParameterSource, rs -> {
				while (rs.next()) {
					info.setId(rs.getInt("ID"));
					info.setAge(rs.getInt("AGE"));
					info.setName(rs.getString("NAME"));
					info.setCapital(rs.getString("CITY"));
					info.setState(rs.getString("STATE"));
					info.setUuidCode(rs.getString("UUID_CODE"));
				}
				return info;

			});
			return info;
		} catch (DataAccessException e) {
			throw e;
		}
	}
	
	@Override
	public int deleteUsersInfoById(String id) throws Exception {
		try {
			SqlParameterSource sqlParameterSource = new MapSqlParameterSource().
					addValue("id", id);
			int count = namedParameterJdbcTemplate.update(DBQueries.DELETE_USERS_BY_ID, sqlParameterSource);
			return count;
		} catch (DataAccessException e) {
			throw e;
		}
	}
	
	@Override
	public List<PickLIst> findAllStates() throws Exception {
		try {
			List<PickLIst> states = new ArrayList<>();

			namedParameterJdbcTemplate.query(DBQueries.SELECT_ALL_STATES, rs -> {
				while (rs.next()) {
					PickLIst state = new PickLIst();
					state.setId(rs.getInt("ID"));
					state.setName(rs.getString("STATE_NAME"));
					state.setCode(rs.getString("CODE"));
					state.setCapital(rs.getString("CAPITAL"));
					states.add(state);
				}

				return states;
			});
			return states;
		} catch (DataAccessException e) {
			throw e;
		}
	}

}
