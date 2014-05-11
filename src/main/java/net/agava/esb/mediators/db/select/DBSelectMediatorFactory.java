package net.agava.esb.mediators.db.select;

import org.apache.axiom.om.OMElement;
import org.apache.synapse.Mediator;
import org.apache.synapse.config.xml.AbstractDBMediatorFactory;

import javax.xml.namespace.QName;
import java.util.Properties;

/**
 Factory for net.agava.esb.mediators.db.select.DBSelectMediator instances.

 Configuration syntax:

 <dbselect>
    <connection>
        <pool>
        (
            <driver/>
            <url/>
            <user/>
            <password/>
        |
            <dsName/>
            <icClass/>
            <url/>
            <user/>
            <password/>
        )
            <property name="name" value="value"/>*
        </pool>
    </connection>
    <statement>
        <sql>SELECT * FROM table WHERE field1 = ?</sql>
        <parameter [value="" | expression=""] type="int|string"/>*
        <result name="string" column="int|string"/>*
    </statement>
 </dbselect>

*/
public class DBSelectMediatorFactory extends AbstractDBMediatorFactory {



    @Override
    protected Mediator createSpecificMediator(OMElement element, Properties properties) {
        DBSelectMediator mediator = new DBSelectMediator();
        buildDataSource(element, mediator);
        processStatements(element, mediator);
        return mediator;
    }

    /**
     * Reads the data source configuration for all mediators based on the <code>AbstractDBMediator</code>
     * and stores the configuration in the mediator for datasource initialization and de-serialization.
     *
     */
    @Override
    public QName getTagQName() {
        return DBSelectMediatorConstants.DB_SELECT_QNAME;
    }
}
