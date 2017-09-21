package com.metamorf.eform.common.gson;


public class JSONExclusionStrategy  {
	
//	 private static HashMap<Class<?>,String[]> excludedFields;  
//     
//     public JSONExclusionStrategy(Map<String, Object> objectMaps){  
//         excludedFields = new HashMap<>();  
//         for(Entry<String, Object> entry : objectMaps.entrySet()){  
//             Object object = entry.getValue();
//	         //class maps to array of fields to skip in class
//	         Field[] fields = ReflectionUtils.getInheritedPrivateFields(object.getClass());
//	         String[] ignoreFields = {};
//	         
//	         for (Field field : fields) {
//				Ignore ignore = field.getAnnotation(Ignore.class);
//				if(ignore != null){
//					ignoreFields = ArrayUtils.addAll(ignoreFields, field.getName());
//				}
//			}
//	         
//	         excludedFields.put(object.getClass(), ignoreFields);  
//         }  
//           
//         //all arrays of fields are sorted lexically for faster lookup  
//         for(Entry<Class<?>,String[]> entry : excludedFields.entrySet()){  
//             Arrays.sort(entry.getValue());  
//         }  
//     }  
//       
//
//     public boolean shouldSkipClass(Class<?> arg0) {  
//         return false;  
//     }  
//
//     public boolean shouldSkipField(FieldAttributes fieldAttributes) {  
//         if(excludedFields.containsKey(fieldAttributes.getDeclaredClass())){  
//             return Arrays.binarySearch(excludedFields.get(fieldAttributes.getDeclaredClass()),fieldAttributes.getName())>=0;   
//         }  
//         return false;  
//     }  
}
