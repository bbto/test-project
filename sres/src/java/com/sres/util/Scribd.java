package com.sres.util;

import com.sres.util.ClientHttpRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author German Garcia
 */
//TODO: This class needs a litle more work, and some documentation comments
public class Scribd {
    /* Unofficial Scribd Java Class library */

    public String api_key;
    public String secret;
    private String url;
    public String session_key;
    public String user_id;
    public String my_user_id;

    private String errorCode;
    private String errorMessage;

    public Scribd(String api_key, String secret) {
        this.api_key = api_key;
        this.secret = secret;
        this.url = "http://api.scribd.com/api?api_key=" + this.api_key;
    }

    /**
     * Upload a document from a file
     * @param string file : relative path to file
     * @param Hashtable optional : hash containing none or some of these parameters
     * @optional string doc_type : PDF, DOC, TXT, PPT, etc.
     * @optional string access : public or private. Default is Public.
     * @optional int rev_id : id of file to modify
     * @optional int{0,1} paid_content : when set to 1, the document becomes protected. Default is 0
     * @optional int{0,1} secure : makes this document an iPaper Secure document
     * @returns array containing doc_id, access_key, and secret_password if necessary.
     */
    public Hashtable upload(String file, Hashtable optional) {
        String method = "docs.upload";
        Hashtable params = new Hashtable();
        params.put("file", file);
        if(optional!=null) {
            Set set = optional.keySet();
            Iterator i = set.iterator();
            while (i.hasNext()) {
                params.put(i, optional.get(i));
            }
        }
        return this.postRequest(method, params);
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
    public ArrayList getList() {
        String method = "docs.getList";
        Hashtable result = this.postRequest(method, null);
        return (ArrayList) result.get("resultset");
    }

    /**
     * Get the current conversion status of a document
     * @param int $doc_id : document id
     * @return string containing "DISPLAYABLE", "DONE", "ERROR", or "PROCESSING" for the current document.
     */
    public String getConversionStatus(String doc_id) {
        String method = "docs.getConversionStatus";
        Hashtable params = new Hashtable();
        params.put("doc_id", doc_id);
        Hashtable result = this.postRequest(method, params);
        return (String) result.get("conversion_status");
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
    public ArrayList search(String query, String num_results,
            String num_start, String scope) {
        String method = "docs.search";
        Hashtable params = new Hashtable();
        params.put("query", query);
        params.put("num_results", num_results);
        params.put("num_start", num_start);
        params.put("scope", scope);
        Hashtable result = this.postRequest(method, params);
        return (ArrayList) result.get("result_set");
    }

    /**
     * Login as a user
     * @param string $username : username of user to log in
     * @param string $password : password of user to log in
     * @return array containing session_key, name, username, and user_id of the user;
     */
    public boolean login(String username, String password) {
        String method = "user.login";
        Hashtable params = new Hashtable();
        params.put("username", username);
        params.put("password", password);
        Hashtable result = this.postRequest(method, params);
        if (result!=null && result.containsKey("user_id")) {
            this.user_id = (String) result.get("user_id");
            this.session_key = (String) result.get("session_key");
            return true;
        }
        return false;
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
        Hashtable result = null;

        params.put("method", method);
        if (session_key != null) {
            params.put("session_key", session_key);
        }
        if (my_user_id != null) {
            params.put("my_user_id", my_user_id);
        }

        Hashtable post_params = new Hashtable();
        Set<String> set = params.keySet();
        Iterator i = set.iterator();
        while (i.hasNext()) {
            Object key = i.next();
            String val = params.get(key).toString();
            if (!val.isEmpty()) {
                if (!key.toString().equals("file") && val.startsWith("@")) {
                    val = (char) (32) + val;
                }
                post_params.put(key, val);
            }
        }
        if (!post_params.containsKey("api_sig")) {
            String md5 = generate_sig(params, secret);
            if (md5 != null) {
                post_params.put("api_sig", md5);
            }
        }
        //System.out.println(post_params.toString());

        try {
            ClientHttpRequest request = new ClientHttpRequest(url);
            Set<String> pset = post_params.keySet();
            i = pset.iterator();
            while (i.hasNext()) {
                Object key = i.next();
                String val = post_params.get(key).toString();
                if (key.toString().equals("file")) {
                    request.setParameter(key.toString(), new File(val));
                } else {
                    request.setParameter(key.toString(), val);
                }
            }
            InputStream is = request.post();
            /*BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            System.out.println(bf.readLine());
            System.out.println(bf.readLine());
            System.out.println(bf.readLine());
            System.out.println(bf.readLine());
            System.out.println(bf.readLine());*/

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);

            String status = doc.getFirstChild().getAttributes().getNamedItem("stat").getNodeValue();
            if (status.equals("ok")) {
                result = new Hashtable();
                NodeList nodes = doc.getFirstChild().getChildNodes();
                for (int index = 0; index < nodes.getLength(); index++) {
                    Node node = nodes.item(index);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        result.put(node.getNodeName(), node.getTextContent());
                        //System.out.println("JODA: " + node.getNodeName() + " = " + node.getTextContent());
                    }
                }
            }
            if (status.equals("fail")) {
                NodeList nodes = doc.getFirstChild().getChildNodes();
                for (int index = 0; index < nodes.getLength(); index++) {
                    Node node = nodes.item(index);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Node attr1 = node.getAttributes().item(0);
                        Node attr2 = node.getAttributes().item(1);
                        this.errorCode = attr1.getNodeValue();
                        this.errorMessage = attr2.getNodeValue();
                    }
                }
            }
        } catch (SAXException ex) {
            ex.printStackTrace(System.err);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.err);
        }
        return result;
    }

    public String generate_sig(Hashtable params_array, String secret) {
        String str = "";
        Set<String> set = params_array.keySet();
        Iterator i = set.iterator();
        // No estoy seguro de si seben ordenar los parametros
        while (i.hasNext()) {
            Object key = i.next();
            String val = params_array.get(key).toString();
            if (!key.toString().equals("file")) {
                str = str + (key.toString() + val);
            }
        }
        str = secret + str;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace(System.err);
            return null;
        }
        md.update(str.getBytes(), 0, str.length());
        String md5 = new BigInteger(1, md.digest()).toString(16);
        while (md5.length() < 32) {
            md5 = "0" + md5;
        }
        return md5;
    }
}
