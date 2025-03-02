package org.it.ms.inkognito.services;

import org.it.ms.inkognito.entities.Setting;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class SettingService {

	public Response getSetting(Long id) {
		Setting setting = Setting.findById(id);
		if (setting == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(setting).build();
	}

	@Transactional
	public Response createSetting(Setting setting) {
		if (setting != null) {
			Setting existing = Setting.findById(setting.getId());
			if (existing == null) {
				setting.persist();
				return Response.ok(setting).build();
			}
			return Response.status(Response.Status.CONFLICT).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@Transactional
	public Response updateSetting(Long id, Setting updatedSetting) {
		Setting setting = Setting.findById(id);
		if (setting == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		setting.setUserId(updatedSetting.getUserId());
		setting.setLanguage(updatedSetting.getLanguage());
		setting.setTheme(updatedSetting.getTheme());
		setting.setPushNotificationsEnabled(updatedSetting.getPushNotificationsEnabled());
		setting.setNote(updatedSetting.getNote());
		setting.persist();
		return Response.ok(setting).build();
	}

	@Transactional
	public Response deleteSetting(Long id) {
		Setting setting = Setting.findById(id);
		if (setting == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		setting.delete();
		return Response.ok("Setting " + id + " deleted.").build();
	}
}
