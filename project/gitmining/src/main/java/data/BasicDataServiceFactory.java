package data;

import data.service.DataServiceFactory;
import data.service.MassiveDataGetter;
import data.service.SpecificDataGetter;
import data.service.stub.MassiveDataGetter_stub;
import data.service.stub.SpecificDataGetter_stub;

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
