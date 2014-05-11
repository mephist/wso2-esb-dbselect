package net.agava.esb.mediators.db.select;

import org.apache.synapse.SynapseConstants;

import javax.xml.namespace.QName;

/**
 * Class that holds constant for the net.agava.esb.mediators.db.select.DBSelectMediator,
 * net.agava.esb.mediators.db.select.DBSelectMediatorFactory
 * and net.agava.esb.mediators.db.select.DBSelectMediatorSerializer
 */
public class DBSelectMediatorConstants {
    public static final String DB_SELECT_TAG_NAME = "dbselect";
    public static final String DB_SELECT_ROOT_NAME = "DBSelectResult";
    public static final QName DB_SELECT_QNAME = new QName(SynapseConstants.SYNAPSE_NAMESPACE, DB_SELECT_TAG_NAME);
    public static final String DB_SELECT_NS="http://esb.agava.net/mediators/db/dbselect";
    public static final String DB_SELECT_NS_PREFIX="dbs";
    public static final String DB_SELECT_PROPERTY_NAME="DB_SELECT_RESULT";
}
