package com.daiwei.project.backend.system.model.base;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 *
 * @author  david:
 * @date 创建时间：2017年8月1日 下午8:49:39
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@MappedSuperclass
public abstract class BaseEntity<ID> implements Serializable, IdKeyModel<ID> {

	@Transient
	private static final long serialVersionUID = 1L;

	// geter and seter
		/** The deleted. */
		//"是否删除"
		protected Boolean deleted;

		/**
		 * Gets the deleted.
		 * 
		 * @return the deleted
		 */
		public Boolean getDeleted() {
			return deleted;
		}

		/**
		 * Sets the deleted.
		 * 
		 * @param deleted
		 *            the new deleted
		 */
		public void setDeleted(Boolean deleted) {
			this.deleted = deleted;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		@SuppressWarnings("unchecked")
	    public boolean equals(Object obj) {
	        if (obj == null) {
	            return false;
	        }
	        if (this == obj) {
	            return true;
	        }
	        if (!(obj instanceof BaseEntity)) {
	            return false;
	        }
			BaseEntity<ID> model = (BaseEntity<ID>) obj;
	        return model.getId() == this.getId();
	    }


	    
	    /* (non-Javadoc)
	     * @see java.lang.Object#toString()
	     */
	    @Override  
	    public String toString() {
	        return this.getClass().getName() + " [ID=" + this.getId() + "]";
	    }
	    
	    /* (non-Javadoc)
	     * @see java.lang.Object#hashCode()
	     */
	    @Override
	    public int hashCode() {
	    	// TODO Auto-generated method stub
	    	return super.hashCode();
	    }

	    
	    
}
