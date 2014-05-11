package net.agava.esb.mediators.db.select;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.db.AbstractDBMediator;
import org.apache.synapse.mediators.db.Statement;
import org.apache.synapse.SynapseLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBSelectMediator extends AbstractDBMediator {

    protected void processStatement(Statement stmnt, MessageContext msgCtx) {
        Connection con = null;
        ResultSet rs = null;
        try {
            con = this.getDataSource().getConnection();
            PreparedStatement ps = getPreparedStatement(stmnt, con, msgCtx);
            rs = ps.executeQuery();

            OMFactory omFactory = msgCtx.getEnvelope().getOMFactory();
            OMElement rootDBSelect = omFactory.createOMElement(DBSelectMediatorConstants.DB_SELECT_ROOT_NAME, DBSelectMediatorConstants.DB_SELECT_NS, DBSelectMediatorConstants.DB_SELECT_NS_PREFIX);
            while (rs.next()) {
                OMElement dbLookupResult = omFactory.createOMElement("row", DBSelectMediatorConstants.DB_SELECT_NS, DBSelectMediatorConstants.DB_SELECT_NS_PREFIX);
                for (String propName : stmnt.getResultsMap().keySet()) {
                    String columnStr =  stmnt.getResultsMap().get(propName);
                    
                    OMElement result = omFactory.createOMElement(propName, DBSelectMediatorConstants.DB_SELECT_NS, DBSelectMediatorConstants.DB_SELECT_NS_PREFIX);
                    result.addAttribute(omFactory.createOMAttribute("name", null, columnStr));

                    result.setText(String.valueOf(rs.getObject(columnStr)));
                    dbLookupResult.addChild(result);
                }
                rootDBSelect.addChild(dbLookupResult);
            }
            msgCtx.setProperty(DBSelectMediatorConstants.DB_SELECT_PROPERTY_NAME, rootDBSelect);
        } catch (SQLException e) {
            log.error("Error executing SQL statement : " + stmnt.getRawStatement() + " against DataSource : " + getDSName(), e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    log.warn("Error occurred while trying to close result set.", e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    log.warn("Error occurred while trying to close connection.", e);
                }
            }
        }
    }
}

