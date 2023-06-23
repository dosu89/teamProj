package material;

public class MaterialDTO {
	private String m_code;
	private String m_name;
	private String m_group;
	
	public MaterialDTO() {}
	
	public MaterialDTO(String m_code, String m_name, String m_group) {
		super();
		this.m_code = m_code;
		this.m_name = m_name;
		this.m_group = m_group;
	}

	public String getM_code() {
		return m_code;
	}

	public void setM_code(String m_code) {
		this.m_code = m_code;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_group() {
		return m_group;
	}

	public void setM_group(String m_group) {
		this.m_group = m_group;
	}
}
