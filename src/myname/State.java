/**
 * 
 */
package myname;

import java.io.Serializable;

/**
 * State Java Bean
 * 
 * @author tim
 *
 */
public class State implements Serializable {
	private static final long serialVersionUID = 2L;
	
	private String stateID;
	private String stateCode;
	private String stateName;
	
	/**
	 * 
	 */
	public State() {
		super();
	}

	/**
	 * @return the stateID
	 */
	public String getStateID() {
		return stateID;
	}

	/**
	 * @param stateID the stateID to set
	 */
	public void setStateID(String stateID) {
		this.stateID = stateID;
	}

	/**
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
