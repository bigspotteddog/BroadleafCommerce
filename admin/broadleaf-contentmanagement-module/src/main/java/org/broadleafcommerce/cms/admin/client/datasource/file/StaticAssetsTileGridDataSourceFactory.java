/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.broadleafcommerce.cms.admin.client.datasource.file;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import org.broadleafcommerce.cms.admin.client.datasource.CeilingEntities;
import org.broadleafcommerce.openadmin.client.datasource.DataSourceFactory;
import org.broadleafcommerce.openadmin.client.datasource.dynamic.CustomCriteriaTileGridDataSource;
import org.broadleafcommerce.openadmin.client.datasource.dynamic.module.BasicClientEntityModule;
import org.broadleafcommerce.openadmin.client.datasource.dynamic.module.DataSourceModule;
import org.broadleafcommerce.openadmin.client.dto.ForeignKey;
import org.broadleafcommerce.common.presentation.client.OperationType;
import org.broadleafcommerce.openadmin.client.dto.OperationTypes;
import org.broadleafcommerce.openadmin.client.dto.PersistencePerspective;
import org.broadleafcommerce.openadmin.client.service.AppServices;

/**
 * 
 * @author jfischer
 *
 */
public class StaticAssetsTileGridDataSourceFactory implements DataSourceFactory {

	public static CustomCriteriaTileGridDataSource dataSource = null;

	public void createDataSource(String name, OperationTypes operationTypes, Object[] additionalItems, AsyncCallback<DataSource> cb) {
		if (dataSource == null) {
			operationTypes = new OperationTypes(OperationType.BASIC, OperationType.BASIC, OperationType.BASIC, OperationType.BASIC, OperationType.BASIC);
			PersistencePerspective persistencePerspective = new PersistencePerspective(operationTypes, new String[] {}, new ForeignKey[]{});
			DataSourceModule[] modules = new DataSourceModule[]{
				new BasicClientEntityModule(CeilingEntities.STATICASSETS, persistencePerspective, AppServices.DYNAMIC_ENTITY)
			};
			dataSource = new CustomCriteriaTileGridDataSource(name, persistencePerspective, AppServices.DYNAMIC_ENTITY, modules, true, true, true, true, true);
            dataSource.setCustomCriteria(new String[]{"assetListUi"});
			dataSource.buildFields(null, false, cb);
		} else {
			if (cb != null) {
				cb.onSuccess(dataSource);
			}
		}
	}

}
