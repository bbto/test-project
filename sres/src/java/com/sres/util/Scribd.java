package com.sres.util;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.util.Hashtable;
import java.util.Iterator;

/**
 *
 * @author bbto
 */
public class Scribd {
    /* Unofficial Scribd Java Class library */

    public String api_key;
    public String secret;
    private String url;
    public String session_key;
    public String my_user_id;
    private String error;

    public Scribd(String api_key, String secret) {
        this.api_key = api_key;
        this.secret = secret;
        this.url = "http://api.scribd.com/api?api_key=" + this.api_key;
    }

    /**
     * Upload a document from a file
     * @param string file : relative path to file
     * @param string doc_type : PDF, DOC, TXT, PPT, etc.
     * @param string access : public or private. Default is Public.
     * @param int rev_id : id of file to modify
     * @return array containing doc_id, access_key, and secret_password if necessary.
     */
    public Hashtable upload(String file, String doc_type, String access, String rev_id) {
        String method = "docs.upload";
        Hashtable params = new Hashtable();
        if (doc_type != null) {
            params.put("doc_type", doc_type);
        }
        if (access != null) {
            params.put("access", access);
        }
        if (file != null) {
            params.put("file", file);
        }
        //$params['file'] = "@".$file;

        Hashtable result = this.postRequest(method, params);
        return result;
    }

    /**
     * Upload a document from a Url
     * @param string $url : absolute URL of file
     * @param string $doc_type : PDF, DOC, TXT, PPT, etc.
     * @param string $access : public or private. Default is Public.
     * @return array containing doc_id, access_key, and secret_password if necessary.
     */
    public Hashtable uploadFromUrl(String url, String doc_type, String access, String rev_id) {
        String method = "docs.uploadFromUrl";
        Hashtable params = new Hashtable();
        if (url != null) {
            params.put("url", url);
        }
        if (access != null) {
            params.put("access", access);
        }
        if (rev_id != null) {
            params.put("rev_id", rev_id);
        }
        if (doc_type != null) {
            params.put("doc_type", doc_type);
        }

        Hashtable data_array = this.postRequest(method, params);
        return data_array;
    }

    /**
     * Get a list of the current users files
     * @return array containing doc_id, title, description, access_key, and conversion_status for all documents
     */
    public Hashtable getList() {
        String method = "docs.getList";
        Hashtable result = this.postRequest(method, null);
        return result.get("resultset");
    }

    /**
     * Get the current conversion status of a document
     * @param int $doc_id : document id
     * @return string containing DISPLAYABLE", "DONE", "ERROR", or "PROCESSING" for the current document.
     */
    public Hashtable getConversionStatus(String doc_id) {
        String method = "docs.getConversionStatus";
        Hashtable params = new Hashtable();
        params.put("doc_id", doc_id);
        String result = this.postRequest(method, params);
        return result.get("conversion_status");
    }

    /**
     * Get settings of a document
     * @return array containing doc_id, title , description , access, tags, show_ads, license, access_key, secret_password
     */
    public Hashtable getSettings(String doc_id) {
        String method = "docs.getSettings";
        Hashtable params = new Hashtable();
        params.put("doc_id", doc_id);
        Hashtable result = this.postRequest(method, params);
        return result;
    }

    /**
     * Change settings of a document
     * @param array $doc_id : document id
     * @param string $title : title of document
     * @param string $description : description of document
     * @param string $access : private, or public
     * @param string $license : "by", "by-nc", "by-nc-nd", "by-nc-sa", "by-nd", "by-sa", "c" or "pd"
     * @param string $access : private, or public
     * @param array $show_ads : default, true, or false
     * @param array $tags : list of tags
     * @return string containing DISPLAYABLE", "DONE", "ERROR", or "PROCESSING" for the current document.
     */
    public Hashtable changeSettings(String doc_id, String title,
            String description, String access, String license,
            String parental_advisory, String show_ads, String tags) {
        String method = "docs.changeSettings";
        Hashtable params = new Hashtable();
        params.put("doc_ids", doc_id);
        params.put("title", title);
        params.put("description", description);
        params.put("access", access);
        params.put("license", license);
        params.put("show_ads", show_ads);
        params.put("tags", tags);
        Hashtable result = this.postRequest(method, params);
        return result;
    }

    /**
     * Delete a document
     * @param int $doc_id : document id
     * @return 1 on success;
     */
    public Hashtable delete(String doc_id) {
        String method = "docs.delete";
        Hashtable params = new Hashtable();
        params.put("doc_id", doc_id);
        Hashtable result = this.postRequest(method, params);
        return result;
    }

    /**
     * Search the Scribd database
     * @param string $query : search query
     * @param int $num_results : number of results to return (10 default, 1000 max)
     * @param int $num_start : number to start from
     * @param string $scope : scope of search, "all" or "user"
     * @return array of results, each of which contain doc_id, secret password, access_key, title, and description
     */
    public Hashtable search(String query, String num_results,
            String num_start, String scope) {
        String method = "docs.search";
        Hashtable params = new Hashtable();
        params.put("query", query);
        params.put("num_results", num_results);
        params.put("num_start", num_start);
        params.put("scope", scope);
        Hashtable result = this.postRequest(method, params);
        return result.get("result_set");
    }

    /**
     * Login as a user
     * @param string $username : username of user to log in
     * @param string $password : password of user to log in
     * @return array containing session_key, name, username, and user_id of the user;
     */
    public Hashtable login(String username, String password) {
        String method = "user.login";
        Hashtable params = new Hashtable();
        params.put("username", username);
        params.put("password", password);
        Hashtable result = this.postRequest(method, params);
        this.session_key = result.get("session_key");
        return result;
    }

    /**
     * Sign up a new user
     * @param string $username : username of user to create
     * @param string $password : password of user to create
     * @param string $email : email address of user
     * @param string $name : name of user
     * @return array containing session_key, name, username, and user_id of the user;
     */
    public Hashtable signup(String username, String password, String email, String name) {
        String method = "user.signup";
        Hashtable params = new Hashtable();
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);
        params.put("name", name);
        Hashtable result = this.postRequest(method, params);
        return result;
    }

    private Hashtable postRequest(String method, Hashtable params) {
        params.put("method", method);
        params.put("session_key", session_key);
        params.put("my_user_id", my_user_id);

        Hashtable post_params = new Hashtable();
        Iterator i = params.iterator();
        while (i.hasNext()) {
            id(params.get("")
            str = itr.next();
            System.out.println(str + ": " + balance.get(str));
        }
        foreach($params as $key = > & $val){ 
                if (!empty($val)){
                    if (is_array($val)) $val = implode(',', $val);
                }
                if ($key != 'file' && substr($val, 0, 1) == "@") {
                    $val = chr(32).$val;
                }
                $post_params[$key] = $val;
            }
        }
        $secret = $this ->  secret;
        $post_params['api_sig']
        = $this->generate_sig($params, $secret);
        $request_url = $this ->  url;

        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $request_url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($ch, CURLOPT_POST, 1);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $post_params);
        $xml = curl_exec($ch);
        $result = simplexml_load_string($xml);
        curl_close($ch);

        if ($result['stat' {
            
        }
        ] == 'fail')
        {

            //This is ineffecient.
            $error_array = (array) $result;
            $error_array = (array) $error_array;
            $error_array = (array) $error_array['error'
              ];
				$error_array = $error_array[
            '@attributes']
              ;
				$this- > error = $error_array['code'
              ];

				throw new Exception($error_array[
            'message']
            , $error_array['code']
             );

				return 0;

        }
        if ($result['stat' {
            
        }
        
                ] == "ok"){
				
				//This is shifty. Works currently though.
				$result = $this- > convert_simplexml_to_array($result);
            if (urlencode((string) $result) == "%0A%0A" && $this ->  error == 0) {
                $result = "1";
                return $result;
            } else {
                return $result;
            }
        }
    }

    params.put ("method"

     , method);
        params.put(

     "session_key", session_key);
        params.put(



         "my_user_id", my_user_id);
        $post_params = array();
        foreach($params 
        as $key = > &  $val

             ) {
            if (  {
            !empty($val))

            {
                if (is_array($val)) {
                    $val = implode(',', $val);
                }
            }
            if ($key != 'file' {
                
            }
            {
            }
            {
            }
            {
            }
            {
            }
            {
            }
            {
            }
            {
            }
            {
            }
            {
            }
            {
            }
            {
            }
            && substr($val, 0, 1) == "@"){












                $val = chr(32).$val;
            }

            $post_params[$key] = $val;
        }
    }
    $secret  = $this ->  secret;
    $post_params




         ['api_sig']
        = $this->generate_sig($params, $secret);
        $request_url = $this - > url;
    $ch  = curl_init();

    curl_setopt($ch, CURLOPT_URL, $request_url);

    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);

    curl_setopt($ch, CURLOPT_POST, 1);

    curl_setopt($ch, CURLOPT_POSTFIELDS, $post_params);
    $xml  = curl_exec($ch);
    $result  = simplexml_load_string($xml);

    curl_close($ch);
    if



































    ($result['stat' {
            
        }
        {
        }
        {
        }
        {
        }
        {
        }
        {
        }
        {
        }
        {
        }
        {
        }
        {
        }
        {
        }






           ]
        == 'fail')
        {

            //This is ineffecient.
            $error_array = (array) $result;
            $error_array = (array) $error_array;
            $error_array = (array) $error_array[
        'error'

          ];
				$error_array = $error_array[











        '@attributes']
          ;
				$this-            > error = $error_array['code'
          ];

				throw new Exception($error_array[











        'message']
        , $error_array['code']
         );

				return 0;

    }
    if



































    ($result['stat' {
            
        }
        {
        }
        {
        }
        {
        }
        {
        }
        {
        }
        {
        }
        {
        }
        {
        }
        {
        }
        {
        }




            ]










               == "ok"){

				//This is shifty. Works currently though.
				$result = $this-            > convert_simplexml_to_array($result);
        if (urlencode((string) $result) == "%0A%0A" && $this ->  error == 0) {
            $result = "1";
            return $result;
        } else {
            return $result;
        }
    }
}

public static function generate_sig(

$params_array, $secret) {
        $str = ''
        ;

        ksort($params_array

);
        // Note: make sure that the signature parameter is not already included in
        //       $params_array.
        foreach   ($params_array

as $k=           > $v












































         )
































        {
        $str


               .= $k . $v;
		}

$str = $secret . $str;

		return

md5($str);
	}

public static function convert_simplexml_to_array(

$sxml) {
		$arr = array();
		if

($sxml) {
		  foreach ($sxml as $k =           > $v


                 
                 
                 



                 
                 
                 
                 ) {
                    if($arr[$k]){
					$arr[$k." "
                    .(count($arr) + 1)]
                    = self:























































































          : convert_simplexml_to_array($v);
				}

else{
					$arr[$k] = self::convert_simplexml_to_array($v);
				}

}
		}
		if (sizeof($arr) > 0) {
            return $arr;
        }

else {
            return (string) $sxml;
        }
}
}

}
