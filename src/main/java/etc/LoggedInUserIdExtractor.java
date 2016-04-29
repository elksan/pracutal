package etc;

import ninja.Context;
import ninja.params.ArgumentExtractor;


public class LoggedInUserIdExtractor implements ArgumentExtractor<Integer> {

    @Override
    public Integer extract(Context context) {
        
        // if we got no cookies we break:
        if (context.getSession() != null) {
            
            String userId = context.getSession().get("userId");
            
            return Integer.parseInt(userId);
            
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
