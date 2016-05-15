package data;

import data.service.DataServiceFactory;
import data.service.ListDataGetter;
import data.service.MassiveDataGetter;
import data.service.SpecificDataGetter;

public class BasicDataServiceFactory extends DataServiceFactory {

	@Override
	public MassiveDataGetter getMassiveDataGetter() {
		return MassiveDataGetter.getInstance();
	}

	@Override
	public SpecificDataGetter getSpecificDataGetter() {
		return SpecificDataGetter.getInstance();
	}

	@Override
	public ListDataGetter getListDataGetter() {
		return ListDataGetter.getInstance();
	}

}
