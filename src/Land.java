
public class Land {

	String landName;
	String landowner;
	int villaPrice;				
	int buildingPrice;				
	int hotelPrice;
	int landmarkPrice;
	int villaCheckCount;
	int buildingCheckCount;
	int hotelCheckCount;
	int landmarkCheckCount;
	int amountVilla;
	int amountBuilding;
	int amountHotel;
	int amountLandmark;
	int constructionCost; // 건설비용
	
	/*땅 객체 배열 생성(위치 지역명 , 빌라 값, 빌딩 값, 호텔 값, 랜드마크 값, 빌라 선택 횟수, 빌딩 선택 횟수, 호텔 선택 횟수,
					랜드마크 선택 횟수, 빌라 갯수, 빌딩 갯수, 호텔 갯수, 랜드마크 갯수, 건설비용, 땅 주인)*/
	Land(String landName, int villaPrice, int buildingPrice, int hotelPrice , int landmarkPrice, 
			int villaCheckCount, int buildingCheckCount, int hotelCheckCount, int landmarkCheckCount,
			int amountVilla, int amountBuilding, int amountHotel, int amountLandmark, int constructionCost, String landowner){

		this.landName = landName;
		this.villaPrice = villaPrice;
		this.buildingPrice = buildingPrice;
		this.hotelPrice = hotelPrice;
		this.landmarkPrice = landmarkPrice;
		this.villaCheckCount = villaCheckCount;
		this.buildingCheckCount = buildingCheckCount;
		this.hotelCheckCount = hotelCheckCount;
		this.landmarkCheckCount = landmarkCheckCount;
		this.constructionCost = constructionCost;
		this.landowner = landowner;
		this.amountVilla = amountVilla;
		this.amountBuilding = amountBuilding;
		this.amountHotel = amountHotel;
		this.amountLandmark = amountLandmark;

	}
	
	
}
