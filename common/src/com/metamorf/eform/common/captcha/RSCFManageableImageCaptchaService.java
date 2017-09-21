/**
 * Customized by Faisal Reza  
 */

package com.metamorf.eform.common.captcha;

import com.octo.captcha.engine.CaptchaEngine;
import com.octo.captcha.engine.image.gimpy.DefaultGimpyEngine;
import com.octo.captcha.service.captchastore.CaptchaStore;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.AbstractManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;


public class RSCFManageableImageCaptchaService extends AbstractManageableImageCaptchaService implements ImageCaptchaService{

	public RSCFManageableImageCaptchaService()
	{
		super(new FastHashMapCaptchaStore(), new DefaultGimpyEngine(), 180, 100000, 75000);
	}
	
	public RSCFManageableImageCaptchaService(int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize, int captchaStoreLoadBeforeGarbageCollection)
	{
		super(new FastHashMapCaptchaStore(), new DefaultGimpyEngine(), minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize, captchaStoreLoadBeforeGarbageCollection);
	}

	public RSCFManageableImageCaptchaService(CaptchaStore captchaStore, CaptchaEngine captchaEngine, int minGuarantedStorageDelayInSeconds, int maxCaptchaStoreSize, int captchaStoreLoadBeforeGarbageCollection)
	{
		super(captchaStore, captchaEngine, minGuarantedStorageDelayInSeconds, maxCaptchaStoreSize, captchaStoreLoadBeforeGarbageCollection);
	}
}