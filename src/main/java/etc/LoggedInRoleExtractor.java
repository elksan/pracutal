package etc;

import ninja.Context;
import ninja.params.ArgumentExtractor;


public class LoggedInRoleExtractor implements ArgumentExtractor<Integer> {

    @Override
    public Integer extract(Context context) {
        
        // if we got no cookies we break:
        if (context.getSession() != null) {
            
            String role = context.getSession().get("role");
            
            return Integer.parseInt(role);
            
        }
        
        return null;
    }

    @Override
    public Class<Integer> getExtractedType() {
        return Integer.class;
    }

    @Override
    public String getFieldName() {
        return null;
    }


}
