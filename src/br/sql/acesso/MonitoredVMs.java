/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.acesso;

// imports necessários
import java.net.URISyntaxException;
import java.util.Set;
import sun.jvmstat.monitor.HostIdentifier;
import sun.jvmstat.monitor.MonitoredHost;
import sun.jvmstat.monitor.MonitoredVm;
import sun.jvmstat.monitor.MonitoredVmUtil;
import sun.jvmstat.monitor.MonitorException;
import sun.jvmstat.monitor.VmIdentifier;

/**
 *
 * @author Juliano Alves Medina
 */
public class MonitoredVMs {

    /**
     *
     * @param processPid
     * @return
     */
    public static boolean getMonitoredVMs(int processPid) {
        MonitoredHost host;
        Set vms;
        try {
            host = MonitoredHost.getMonitoredHost(new HostIdentifier((String) null));
            vms = host.activeVms();
        } catch (java.net.URISyntaxException sx) {
            throw new InternalError(sx.getMessage());
        } catch (MonitorException mx) {
            throw new InternalError(mx.getMessage());
        }
        MonitoredVm mvm = null;
        String processName = null;
        try {
            mvm = host.getMonitoredVm(new VmIdentifier(String.valueOf(processPid)));
            processName = MonitoredVmUtil.commandLine(mvm);
            processName = processName.substring(processName.lastIndexOf("\\") + 1, processName.length());
            mvm.detach();
        } catch (URISyntaxException | MonitorException ex) {
        }
        // Essa linha é somente para verificar o nome do processo aberto. Pode ser retirada
        // JOptionPane.showMessageDialog(null, processName);
        for (Object vmid : vms) {
            if (vmid instanceof Integer) {
                int pid = ((Integer) vmid);
                String name = vmid.toString(); // default to pid if name not available
                try {
                    mvm = host.getMonitoredVm(new VmIdentifier(name));
                    // use the command line as the display name
                    name = MonitoredVmUtil.commandLine(mvm);
                    name = name.substring(name.lastIndexOf("\\") + 1, name.length());
                    mvm.detach();
                    if ((name.equalsIgnoreCase(processName)) && (processPid != pid)) {
                        return false;
                    }
                } catch (URISyntaxException | MonitorException x) {
                    // ignore
                }
            }
        }
        return true;
    }

}
