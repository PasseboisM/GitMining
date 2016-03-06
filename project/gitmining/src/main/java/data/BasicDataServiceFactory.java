package data;

import data.service.DataServiceFactory;
import data.service.MassiveDataGetter;
import data.service.SpecificDataGetter;
import data.service.stub.MassiveDataGetter_stub;

public class BasicDataServiceFactory extends DataServiceFactory {

	@Override
	public MassiveDataGetter getMassiveDataGetter() {
		// TODO 使用stub进行测试
		return new MassiveDataGetter_stub();
	}

	@Override
	public SpecificDataGetter getSpecificDataGetter() {
		// TODO Auto-generated method stub
		return null;
	}

}
