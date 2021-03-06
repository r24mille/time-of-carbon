package name.reidmiller.timeofemissions.model;

import name.reidmiller.aesoreports.model.GeneratorType;
import name.reidmiller.iesoreports.client.GeneratorOutputCapabilityClient.FuelType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.misoenergy.ria.binding.FuelCategory;

/**
 * Note that {@link #compareTo(CommonFuelType)} is final and uses the natural
 * ordering of Enum which sorts according to the order which constants are
 * declared. Currently the fuel types are being ordered according to carbon
 * dioxide emissions and dispatchability.
 * 
 * TODO For now, since this is primarily for IESO, OTHER was given the
 * carbon-dioxide emissions and HTML color of biomass since that's primarily what Ontario's
 * "Other" generators are.
 */
public enum CommonFuelType {
	NUCLEAR(16, "#F9A848"), WIND(12, "#79D24C"), SOLAR_PV(46), SOLAR_THERMAL(
			22), GEOTHERMAL(45), HYDROELECTRIC(4, "#204C79"), DISPATCHABLE_LOAD(
			0), OTHER(18, "#999966"), BIOMASS(18, "#999966"), NATURAL_GAS(469, "#A52A2A"), COAL(1001,
			"#000000");

	private static Logger logger = LogManager.getLogger(CommonFuelType.class);
	private double gramsCarbonDioxidePerKWHe;
	private String graphColor;

	CommonFuelType(double gramsCarbonDioxidePerKWHe) {
		this.gramsCarbonDioxidePerKWHe = gramsCarbonDioxidePerKWHe;
	}

	CommonFuelType(double gramsCarbonDioxidePerKWHe, String graphColor) {
		this.gramsCarbonDioxidePerKWHe = gramsCarbonDioxidePerKWHe;
		this.graphColor = graphColor;
	}

	/**
	 * Return the proper CommonFuelType for the {@link FuelType} class from
	 * IESO's public report library.
	 * 
	 * @param fuelType
	 *            IESO fuel type
	 * @return CommonFuelType
	 */
	public static CommonFuelType valueOfFuelType(FuelType fuelType) {
		switch (fuelType) {
		case COAL:
			return COAL;
		case GAS:
			return NATURAL_GAS;
		case HYDRO:
			return HYDROELECTRIC;
		case NUCLEAR:
			return NUCLEAR;
		case OTHER:
			// TODO Check if this is generally true for IESO
			return BIOMASS;
		case WIND:
			return WIND;
		default:
			logger.info("Default case statement reached. fuelType is "
					+ fuelType);
			return OTHER;
		}
	}

	/**
	 * Return the proper CommonFuelType for the {@link FuelCategory} class from
	 * MISO's public report library.
	 * 
	 * @param fuelCategory
	 *            MISO fuel category
	 * @return CommonFuelType
	 */
	public static CommonFuelType valueOfFuelCategory(FuelCategory fuelCategory) {
		switch (fuelCategory) {
		case COAL:
			return COAL;
		case NATURAL_GAS:
			return NATURAL_GAS;
		case NUCLEAR:
			return NUCLEAR;
		case WIND:
			return WIND;
		default:
			logger.info("Default case statement reached. fuelCategory is "
					+ fuelCategory);
			return OTHER;
		}
	}

	/**
	 * Return the proper CommonFuelType for the {@link GeneratorType} class from
	 * AESO's public report library.
	 * 
	 * @param generatorType
	 *            AESO generator type
	 * @return CommonFuelType
	 */
	public static CommonFuelType valueOfGeneratorType(
			GeneratorType generatorType) {
		switch (generatorType) {
		case COAL:
			return COAL;
		case GAS:
			return NATURAL_GAS;
		case HYDRO:
			return HYDROELECTRIC;
		case WIND:
			return WIND;
		default:
			logger.info("Default case statement reached. generatorType is "
					+ generatorType);
			return OTHER;
		}
	}

	public String getGraphColor() {
		return graphColor;
	}

	public double getGramsCarbonDioxidePerKWHe() {
		return this.gramsCarbonDioxidePerKWHe;
	}

	public double getGramsCarbonDioxideForKWHeValue(double kilowattHours) {
		return (this.getGramsCarbonDioxidePerKWHe() * kilowattHours);
	}

	public double getKilogramsCarbonDioxidePerKWHe() {
		return (this.gramsCarbonDioxidePerKWHe / 1000);
	}

	public double getKilogramsCarbonDioxideForKWHeValue(double kilowattHours) {
		return (this.getKilogramsCarbonDioxidePerKWHe() * kilowattHours);
	}

	public double getKilogramsCarbonDioxidePerMWHe() {
		return this.gramsCarbonDioxidePerKWHe;
	}

	public double getKilogramsCarbonDioxideForMWHeValue(double megawattHours) {
		return (this.getKilogramsCarbonDioxidePerMWHe() * megawattHours);
	}

	public double getMetricTonnesCarbonDioxidePerMWHe() {
		return (this.getKilogramsCarbonDioxidePerMWHe() / 1000);
	}

	public double getMetricTonnesCarbonDioxideForMWHeValue(double megawattHours) {
		return (this.getMetricTonnesCarbonDioxidePerMWHe() * megawattHours);
	}
}
