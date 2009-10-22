package jatools.designer.peer;

import jatools.component.ColumnPanel;
import jatools.component.Component;
import jatools.component.Page;
import jatools.component.Panel;
import jatools.component.table.RowPanel;
import jatools.component.table.TableBase;
import jatools.designer.ReportPanel;



/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
  */
public final class ComponentPeerFactory {
    /**
     * DOCUMENT ME!
     *
     * @param owner DOCUMENT ME!
     * @param target DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static ComponentPeer createPeer(ReportPanel owner, Component target) {
        ComponentPeer peer = null;

        if (target instanceof Page) {
            peer = new PagePeer(owner, target);
        } else if (target instanceof TableBase) {
            peer = new TablePeer(owner, target);
        } else if (target instanceof RowPanel) {
            peer = new RowPanelPeer(owner, target);
        } else if (target instanceof ColumnPanel) {
            peer = new ColumnPanelPeer(owner, target);
        } else if (target instanceof Panel) {
            peer = new PanelPeer(owner, target);
        } else {
            peer = new ComponentPeer(owner, target);
        }

        owner.setComponent2Peer(target, peer);

        return peer;
    }
}
