package data;

import data.service.DataServiceFactory;
import data.service.MassiveDataGetter;
import data.service.SpecificDataGetter;

public class BasicDataServiceFactory extends DataServiceFactory {

	@Override
	public MassiveDataGetter getMassiveDataGetter() {
//		return new MassiveDataGetter_stub();
		return MassiveDataGetter.getInstance();
	}

	@Override
	public SpecificDataGetter getSpecificDataGetter() {
		return SpecificDataGetter.getInstance();
	}

}
