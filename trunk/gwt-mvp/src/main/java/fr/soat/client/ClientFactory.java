/*
 * Copyright 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package fr.soat.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

import fr.soat.client.presenter.DetailPersonPresenter.IDetailPersonView;
import fr.soat.client.presenter.FormPersonPresenter.IFormPersonView;

/**
 * Client Factory 
 * Elle permet de récupérer les vues (singletons), Event bus 
 * 
 * @author Kader
 */
public interface ClientFactory {
	
	EventBus getEventBus();

	PlaceController getPlaceController();

	IDetailPersonView getDetailPersonView();

	IFormPersonView getFormPersonView();

}
