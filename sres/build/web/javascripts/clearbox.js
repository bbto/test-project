


/*                                                                                                                                                                              
	clearbox by pyro
	
	script home:		http://www.clearbox.hu
	email:			clearboxjs(at)gmail(dot)com
	MSN:			pyro(at)radiomax(dot)hu
	support forum 1:	http://www.sg.hu/listazas.php3?id=1172325655

	LICENSZ FELT�TELEK:

	A clearbox szabadon felhaszn�lhat� b�rmilyen nem kereskedelmi jelleg� honlapon, 
	teh�t azokon amelyek nem kereskedelmi tev�kenys�get folytat� c�gek, v�llalatok 
	oldalai; nem tartalmaznak kereskedelmi jelleg� szolg�ltat�st vagy term�k(ek) 
	elad�s(�)t, illetve rekl�moz�s(�)t. A kereskedelmi jelleg� honlapokon val� 
	felhaszn�l�s�r�l �rdekl�dj a k�sz�t�n�l! A clearbox forr�sk�dja nem m�dos�that�. 
	A clearbox a k�sz�t� beleegyez�se n�lk�l p�nz�rt harmadik f�lnek tov�bb nem adhat�!

	LICENSE:

	ClearBox can be used free for all non-commercial web pages. For commercial using, please contact with the developer:

	George Krupa
*/



var	CB_ScriptDir='/sres/javascripts/clearbox';
var	CB_Language='en';



//
//	ClearBox load:
//

	var CB_Scripts = document.getElementsByTagName('script');
	for(i=0;i<CB_Scripts.length;i++){
		if (CB_Scripts[i].getAttribute('src')){
			var q=CB_Scripts[i].getAttribute('src');
			if(q.match('clearbox.js')){
				var url = q.split('clearbox.js');
				var path = url[0];
				var query = url[1].substring(1);
				var pars = query.split('&');
				for(j=0; j<pars.length; j++) {
					par = pars[j].split('=');
					switch(par[0]) {
						case 'config': {
							CB_Config = par[1];
							break;
						}
						case 'dir': {
							CB_ScriptDir = par[1];
							break;
						}
						case 'lng': {
							CB_Language = par[1];
							break;
						}
					}
				}
			}
		}
	}

	if(!CB_Config){
		var CB_Config='default';
	}

	document.write('<link rel="stylesheet" type="text/css" href="'+CB_ScriptDir+'/config/'+CB_Config+'/cb_style.css" />');
	document.write('<script type="text/javascript" src="'+CB_ScriptDir+'/config/'+CB_Config+'/cb_config.js"></script>');
	document.write('<script type="text/javascript" src="'+CB_ScriptDir+'/language/'+CB_Language+'/cb_language.js"></script>');
	document.write('<script type="text/javascript" src="'+CB_ScriptDir+'/core/cb_core.js"></script>');