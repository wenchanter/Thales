package com.wenchanter.thales.core.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseDao  extends SqlMapClientDaoSupport {
	    @Resource
	    public void setSqlMapClientForAutowire(SqlMapClient sqlMapClient) {
	        super.setSqlMapClient(sqlMapClient);
	    }

	    private SqlMapClientTemplate sqlMapClientTemplate = this.getSqlMapClientTemplate();
	    private String namespace;

	    /**
	     * @return the namespace
	     */
	    public String getNamespace() {
	        return namespace;
	    }

	    /**
	     * @param namespace the namespace to set
	     */
	    public void setNamespace(String namespace) {
	        this.namespace = namespace;
	    }

	    /**
	     * To get the full statement name with namespace.
	     *
	     * @param statementName
	     * @return
	     */
	    private String getFullStatementName(String statementName) {
	        return namespace + "." + statementName;
	    }

	    /**
	     * Query for object.
	     *
	     * @param statementName
	     * @return
	     */
	    protected Object queryForObject(String statementName) throws DataAccessException {
	        return sqlMapClientTemplate.queryForObject(getFullStatementName(statementName));
	    }

	    /**
	     * Query for object.
	     *
	     * @param statementName
	     * @param parameterObject
	     * @return
	     * @throws DataAccessException
	     */
	    protected Object queryForObject(String statementName, Object parameterObject) throws DataAccessException {
	        return sqlMapClientTemplate.queryForObject(getFullStatementName(statementName), parameterObject);
	    }

	    /**
	     * Query for list.
	     *
	     * @param statementName
	     * @return
	     * @throws DataAccessException
	     */
		@SuppressWarnings("rawtypes")
		protected List queryForList(String statementName) throws DataAccessException {
	        return sqlMapClientTemplate.queryForList(getFullStatementName(statementName));
	    }

	    /**
	     * Query for list.
	     *
	     * @param statementName
	     * @param parameterObject
	     * @return
	     * @throws DataAccessException
	     */
		@SuppressWarnings("rawtypes")
		protected List queryForList(String statementName, Object parameterObject) throws DataAccessException {
	        return sqlMapClientTemplate.queryForList(getFullStatementName(statementName), parameterObject);
	    }

	    /**
	     * Query for map.
	     *
	     * @param statementName
	     * @param parameterObject
	     * @param keyProperty
	     * @return
	     * @throws DataAccessException
	     */
		@SuppressWarnings("rawtypes")
		protected Map queryForMap(String statementName, Object parameterObject, String keyProperty)
	            throws DataAccessException {
	        return sqlMapClientTemplate.queryForMap(getFullStatementName(statementName), parameterObject, keyProperty);
	    }
		/**
		 *
		 * @param statementName
		 * @param parameterObject
		 * @param keyProperty
		 * @param valueProperty
		 * @return
		 * @throws DataAccessException
		 */
		@SuppressWarnings("rawtypes")
		protected Map queryForMap(String statementName, Object parameterObject, String keyProperty,String valueProperty)
		        throws DataAccessException {
		    return sqlMapClientTemplate.queryForMap(getFullStatementName(statementName), parameterObject, keyProperty,valueProperty);
		}
	    /**
	     * Insert action.
	     *
	     * @param statementName
	     */
	    protected Object insert(String statementName) {
	        return sqlMapClientTemplate.insert(getFullStatementName(statementName));
	    }

	    /**
	     * Insert action with parameter.
	     *
	     * @param statementName
	     * @param parameterObject
	     */
	    protected Object insert(String statementName, Object parameterObject) {
	        return sqlMapClientTemplate.insert(getFullStatementName(statementName), parameterObject);
	    }

	    /**
	     * Update action.
	     *
	     * @param statementName
	     * @return
	     */
	    protected int update(String statementName) {
	        return sqlMapClientTemplate.update(getFullStatementName(statementName));
	    }

	    /**
	     * Update action with parameter.
	     *
	     * @param statementName
	     * @param parameterObject
	     * @return
	     */
	    protected int update(String statementName, Object parameterObject) {
	        return sqlMapClientTemplate.update(getFullStatementName(statementName), parameterObject);
	    }

	    /**
	     * Delete action.
	     *
	     * @param statementName
	     * @return
	     */
	    protected int delete(String statementName) {
	        return sqlMapClientTemplate.delete(getFullStatementName(statementName));
	    }

	    /**
	     * Delete action with parameter.
	     *
	     * @param statementName
	     * @param parameterObject
	     * @return
	     */
	    protected int delete(String statementName, Object parameterObject) {
	        return sqlMapClientTemplate.delete(getFullStatementName(statementName), parameterObject);
	    }


}
