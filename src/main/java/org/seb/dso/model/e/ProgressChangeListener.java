package org.seb.dso.model.e;

import java.util.EventListener;

/**
 * @author sebastian_fl
 *
 */
public interface ProgressChangeListener extends EventListener {
	public void progressChanged(int i);

}
