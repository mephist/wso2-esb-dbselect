package net.agava.esb.mediators.db.select;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMNode;
import org.apache.axiom.om.OMText;
import org.apache.synapse.Mediator;
import org.apache.synapse.SynapseException;
import org.apache.synapse.config.xml.AbstractDBMediatorSerializer;
import org.apache.synapse.config.xml.AbstractMediatorSerializer;
import org.apache.synapse.config.xml.SynapseXPathSerializer;
import org.apache.synapse.mediators.db.Statement;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamConstants;
import java.sql.Types;

/*

 Serializer for net.agava.esb.mediators.db.select.DBSelectMediator objects.

 Serialize into following syntax depending on the mediator config:

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
public class DBSelectMediatorSerializer extends AbstractDBMediatorSerializer {
    @Override
    protected OMElement serializeSpecificMediator(Mediator mediator) {
        if (!(mediator instanceof DBSelectMediator)) {
            handleException("Unsupported mediator passed in for serialization : " + mediator.getType());
        }
        DBSelectMediator dbSelectMediator = (DBSelectMediator) mediator;
        OMElement dbSelectElement = fac.createOMElement(DBSelectMediatorConstants.DB_SELECT_TAG_NAME, synNS);
        saveTracingState(dbSelectElement, mediator);
        serializeDBInformation(dbSelectMediator, dbSelectElement);
        return dbSelectElement;

    }

    @Override
    public String getMediatorClassName() {
        return DBSelectMediator.class.getName();
    }
}
