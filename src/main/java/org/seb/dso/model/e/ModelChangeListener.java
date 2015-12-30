package org.seb.dso.model.e;

import java.util.EventListener;

/**
 * @author sebastian_fl
 *
 */
public interface ModelChangeListener extends EventListener {
	public void modelChanged(ModelChangeEvent e);

}
