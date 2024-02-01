package 개발자괴롭히기;

import java.util.ArrayList;

public class DeveloperController {

	private DeveloperDAO dao = new DeveloperDAO();
	private CharacterDAO cdao = new CharacterDAO();

	public int join(DeveloperDTO dto) {
		return dao.join(dto);
	}

	public DeveloperDTO login(String id, String pw) {
		return dao.login(id, pw);
	}

	public ArrayList<String> CharacterList() {
		return cdao.CharacterList();

	}

	public int delete(String id) {
		return dao.delete(id);
	}

	public int inputStats(CharacterDTO cdto) {

		return cdao.inputStats(cdto);
	}

	public int cDelete(String name) {

		return cdao.cDelete(name);
	}

	
	public ArrayList<Integer> salaryList(){
		return cdao.salaryList();
	}


	
}
