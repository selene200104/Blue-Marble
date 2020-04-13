
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
	int constructionCost;
	
	//땅 이름, 빌라 가격, 빌딩 가격, 호텔 가격, 랜드마크 가격, 빌라 체크 횟수, 빌딩 체크 횟수, 호텔 체크 횟수, 랜드마크 체크 횟수, 구조물 가격
	//건물주인, 빌라 갯수, 빌딩 갯수, 호텔 갯수, 랜드마크 갯수
	Land(String landName, int villaPrice, int buildingPrice, int hotelPrice , int landmarkPrice, 
			int villaCheckCount, int buildingCheckCount, int hotelCheckCount, int landmarkCheckCount,
			int constructionCost, String landowner, int amountVilla, int amountBuilding, int amountHotel,
			int amountLandmark){

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
