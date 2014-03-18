/**
 *
 * Fichier Javascript du socle
 *
 * Version 1.0
 * Version 2.0 : Montée de version boostrap 3
 */


// 	formulaire

	// intégration JQuery validate avec twitter bootstrap
	$.extend($.validator.defaults, {
		    errorClass: 'has-error',
		    validClass: 'has-success',
		    errorElement: 'span',
		    highlight: function (element, errorClass, validClass) {
		        if (element.type === 'radio') {
		            this.findByName(element.name).closest('div.form-group').removeClass(validClass).addClass(errorClass);
		        }else {
		            $(element).closest('div.form-group').removeClass(validClass).addClass(errorClass);
		        }
		    },
		    unhighlight: function (element, errorClass, validClass) {
		        if (element.type === 'radio') {
		            this.findByName(element.name).parent('div').parent('div').removeClass(errorClass).addClass(validClass);
		        } else {
		            $(element).closest('div.form-group').removeClass(errorClass).addClass(validClass);
		        }
		    },
		    errorPlacement: function(error, element) {
		        var wrapperElement = $(element).parent('div.input-append');
		        if (wrapperElement.length == 0) {
		            wrapperElement = $(element).parent('div.input-prepend');
		        }
		        if (wrapperElement.length > 0) {
		            error.insertAfter(wrapperElement);
		        } else {
		            error.insertAfter(element);
		        }
		    }
	});


// fin formulaire

// datatable	 - intégration avec Bootstrap - valable pour tout le monde

	/* Default class modification */
	$.extend( $.fn.dataTableExt.oStdClasses, {
		"sWrapper": "dataTables_wrapper form-inline"
	} );


	/* API method to get paging information */
	$.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
	{
		return {
			"iStart":         oSettings._iDisplayStart,
			"iEnd":           oSettings.fnDisplayEnd(),
			"iLength":        oSettings._iDisplayLength,
			"iTotal":         oSettings.fnRecordsTotal(),
			"iFilteredTotal": oSettings.fnRecordsDisplay(),
			"iPage":          Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
			"iTotalPages":    Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
		};
	};

	/* Bootstrap style pagination control */
	$.extend( $.fn.dataTableExt.oPagination, {
		"bootstrap": {
			"fnInit": function( oSettings, nPaging, fnDraw ) {
				// var oLang = oSettings.oLanguage.oPaginate;
				var fnClickHandler = function ( e ) {
					e.preventDefault();
					if ( oSettings.oApi._fnPageChange(oSettings, e.data.action) ) {
						fnDraw( oSettings );
					}
				};

				$(nPaging).addClass('pagination').append(
					'<ul class="pagination">'+
						'<li><a href="#">&laquo;</a></li>'+
						 '<li><a href="#">&raquo;</a></li>' +
					'</ul>'
				);
				var els = $('a', nPaging);
				$(els[0]).bind( 'click.DT', { action: "previous" }, fnClickHandler );
				$(els[1]).bind( 'click.DT', { action: "next" }, fnClickHandler );
			},

			"fnUpdate": function ( oSettings, fnDraw ) {
				var iListLength = 5;
				var oPaging = oSettings.oInstance.fnPagingInfo();
				var an = oSettings.aanFeatures.p;
				var i, j, sClass, iStart, iEnd, iHalf=Math.floor(iListLength/2);

				if ( oPaging.iTotalPages < iListLength) {
					iStart = 1;
					iEnd = oPaging.iTotalPages;
				}
				else if ( oPaging.iPage <= iHalf ) {
					iStart = 1;
					iEnd = iListLength;
				} else if ( oPaging.iPage >= (oPaging.iTotalPages-iHalf) ) {
					iStart = oPaging.iTotalPages - iListLength + 1;
					iEnd = oPaging.iTotalPages;
				} else {
					iStart = oPaging.iPage - iHalf + 1;
					iEnd = iStart + iListLength - 1;
				}

				for ( i=0, iLen=an.length ; i<iLen ; i++ ) {
					// Remove the middle elements
					$('li:gt(0)', an[i]).filter(':not(:last)').remove();

					// Add the new list items and their event handlers
					for ( j=iStart ; j<=iEnd ; j++ ) {
						sClass = (j==oPaging.iPage+1) ? 'class="active"' : '';
						$('<li '+sClass+'><a href="#">'+j+'</a></li>')
							.insertBefore( $('li:last', an[i])[0] )
							.bind('click', function (e) {
								e.preventDefault();
								oSettings._iDisplayStart = (parseInt($('a', this).text(),10)-1) * oPaging.iLength;
								fnDraw( oSettings );
							} );
					}

					// Add / remove disabled classes from the static elements
					if ( oPaging.iPage === 0 ) {
						$('li:first', an[i]).addClass('disabled');
					} else {
						$('li:first', an[i]).removeClass('disabled');
					}

					if ( oPaging.iPage === oPaging.iTotalPages-1 || oPaging.iTotalPages === 0 ) {
						$('li:last', an[i]).addClass('disabled');
					} else {
						$('li:last', an[i]).removeClass('disabled');
					}
				}
			}
		}
	} );


// fin datatable



// decode appel REST


/**
 * Decode les param\u00e8tres HTML.
 * Cela est utile pour d\u00e9coder les caract\u00e8res HDIV
 * Ex : Remplace le &lt; en chevron
 */
function HtmlDecode(s) {
	var out = "";
	if (s == null)
		return;
	var l = s.length;
	for ( var i = 0; i < l; i++) {
		var ch = s.charAt(i);
		if (ch == '&') {
			var semicolonIndex = s.indexOf(';', i + 1);
			if (semicolonIndex > 0) {
				var entity = s.substring(i + 1, semicolonIndex);
				if (entity.length > 1 && entity.charAt(0) == '#') {
					if (entity.charAt(1) == 'x' || entity.charAt(1) == 'X')
						ch = String
								.fromCharCode(eval("0" + entity.substring(1)));
					else
						ch = String.fromCharCode(eval(entity.substring(1)));
				} else {
					switch (entity) {
					case 'quot':
						ch = String.fromCharCode(0x0022);
						break;
					case 'amp':
						ch = String.fromCharCode(0x0026);
						break;
					case 'lt':
						ch = String.fromCharCode(0x003c);
						break;
					case 'gt':
						ch = String.fromCharCode(0x003e);
						break;
					case 'nbsp':
						ch = String.fromCharCode(0x00a0);
						break;
					case 'iexcl':
						ch = String.fromCharCode(0x00a1);
						break;
					case 'cent':
						ch = String.fromCharCode(0x00a2);
						break;
					case 'pound':
						ch = String.fromCharCode(0x00a3);
						break;
					case 'curren':
						ch = String.fromCharCode(0x00a4);
						break;
					case 'yen':
						ch = String.fromCharCode(0x00a5);
						break;
					case 'brvbar':
						ch = String.fromCharCode(0x00a6);
						break;
					case 'sect':
						ch = String.fromCharCode(0x00a7);
						break;
					case 'uml':
						ch = String.fromCharCode(0x00a8);
						break;
					case 'copy':
						ch = String.fromCharCode(0x00a9);
						break;
					case 'ordf':
						ch = String.fromCharCode(0x00aa);
						break;
					case 'laquo':
						ch = String.fromCharCode(0x00ab);
						break;
					case 'not':
						ch = String.fromCharCode(0x00ac);
						break;
					case 'shy':
						ch = String.fromCharCode(0x00ad);
						break;
					case 'reg':
						ch = String.fromCharCode(0x00ae);
						break;
					case 'macr':
						ch = String.fromCharCode(0x00af);
						break;
					case 'deg':
						ch = String.fromCharCode(0x00b0);
						break;
					case 'plusmn':
						ch = String.fromCharCode(0x00b1);
						break;
					case 'sup2':
						ch = String.fromCharCode(0x00b2);
						break;
					case 'sup3':
						ch = String.fromCharCode(0x00b3);
						break;
					case 'acute':
						ch = String.fromCharCode(0x00b4);
						break;
					case 'micro':
						ch = String.fromCharCode(0x00b5);
						break;
					case 'para':
						ch = String.fromCharCode(0x00b6);
						break;
					case 'middot':
						ch = String.fromCharCode(0x00b7);
						break;
					case 'cedil':
						ch = String.fromCharCode(0x00b8);
						break;
					case 'sup1':
						ch = String.fromCharCode(0x00b9);
						break;
					case 'ordm':
						ch = String.fromCharCode(0x00ba);
						break;
					case 'raquo':
						ch = String.fromCharCode(0x00bb);
						break;
					case 'frac14':
						ch = String.fromCharCode(0x00bc);
						break;
					case 'frac12':
						ch = String.fromCharCode(0x00bd);
						break;
					case 'frac34':
						ch = String.fromCharCode(0x00be);
						break;
					case 'iquest':
						ch = String.fromCharCode(0x00bf);
						break;
					case 'Agrave':
						ch = String.fromCharCode(0x00c0);
						break;
					case 'Aacute':
						ch = String.fromCharCode(0x00c1);
						break;
					case 'Acirc':
						ch = String.fromCharCode(0x00c2);
						break;
					case 'Atilde':
						ch = String.fromCharCode(0x00c3);
						break;
					case 'Auml':
						ch = String.fromCharCode(0x00c4);
						break;
					case 'Aring':
						ch = String.fromCharCode(0x00c5);
						break;
					case 'AElig':
						ch = String.fromCharCode(0x00c6);
						break;
					case 'Ccedil':
						ch = String.fromCharCode(0x00c7);
						break;
					case 'Egrave':
						ch = String.fromCharCode(0x00c8);
						break;
					case 'Eacute':
						ch = String.fromCharCode(0x00c9);
						break;
					case 'Ecirc':
						ch = String.fromCharCode(0x00ca);
						break;
					case 'Euml':
						ch = String.fromCharCode(0x00cb);
						break;
					case 'Igrave':
						ch = String.fromCharCode(0x00cc);
						break;
					case 'Iacute':
						ch = String.fromCharCode(0x00cd);
						break;
					case 'Icirc':
						ch = String.fromCharCode(0x00ce);
						break;
					case 'Iuml':
						ch = String.fromCharCode(0x00cf);
						break;
					case 'ETH':
						ch = String.fromCharCode(0x00d0);
						break;
					case 'Ntilde':
						ch = String.fromCharCode(0x00d1);
						break;
					case 'Ograve':
						ch = String.fromCharCode(0x00d2);
						break;
					case 'Oacute':
						ch = String.fromCharCode(0x00d3);
						break;
					case 'Ocirc':
						ch = String.fromCharCode(0x00d4);
						break;
					case 'Otilde':
						ch = String.fromCharCode(0x00d5);
						break;
					case 'Ouml':
						ch = String.fromCharCode(0x00d6);
						break;
					case 'times':
						ch = String.fromCharCode(0x00d7);
						break;
					case 'Oslash':
						ch = String.fromCharCode(0x00d8);
						break;
					case 'Ugrave':
						ch = String.fromCharCode(0x00d9);
						break;
					case 'Uacute':
						ch = String.fromCharCode(0x00da);
						break;
					case 'Ucirc':
						ch = String.fromCharCode(0x00db);
						break;
					case 'Uuml':
						ch = String.fromCharCode(0x00dc);
						break;
					case 'Yacute':
						ch = String.fromCharCode(0x00dd);
						break;
					case 'THORN':
						ch = String.fromCharCode(0x00de);
						break;
					case 'szlig':
						ch = String.fromCharCode(0x00df);
						break;
					case 'agrave':
						ch = String.fromCharCode(0x00e0);
						break;
					case 'aacute':
						ch = String.fromCharCode(0x00e1);
						break;
					case 'acirc':
						ch = String.fromCharCode(0x00e2);
						break;
					case 'atilde':
						ch = String.fromCharCode(0x00e3);
						break;
					case 'auml':
						ch = String.fromCharCode(0x00e4);
						break;
					case 'aring':
						ch = String.fromCharCode(0x00e5);
						break;
					case 'aelig':
						ch = String.fromCharCode(0x00e6);
						break;
					case 'ccedil':
						ch = String.fromCharCode(0x00e7);
						break;
					case 'egrave':
						ch = String.fromCharCode(0x00e8);
						break;
					case 'eacute':
						ch = String.fromCharCode(0x00e9);
						break;
					case 'ecirc':
						ch = String.fromCharCode(0x00ea);
						break;
					case 'euml':
						ch = String.fromCharCode(0x00eb);
						break;
					case 'igrave':
						ch = String.fromCharCode(0x00ec);
						break;
					case 'iacute':
						ch = String.fromCharCode(0x00ed);
						break;
					case 'icirc':
						ch = String.fromCharCode(0x00ee);
						break;
					case 'iuml':
						ch = String.fromCharCode(0x00ef);
						break;
					case 'eth':
						ch = String.fromCharCode(0x00f0);
						break;
					case 'ntilde':
						ch = String.fromCharCode(0x00f1);
						break;
					case 'ograve':
						ch = String.fromCharCode(0x00f2);
						break;
					case 'oacute':
						ch = String.fromCharCode(0x00f3);
						break;
					case 'ocirc':
						ch = String.fromCharCode(0x00f4);
						break;
					case 'otilde':
						ch = String.fromCharCode(0x00f5);
						break;
					case 'ouml':
						ch = String.fromCharCode(0x00f6);
						break;
					case 'divide':
						ch = String.fromCharCode(0x00f7);
						break;
					case 'oslash':
						ch = String.fromCharCode(0x00f8);
						break;
					case 'ugrave':
						ch = String.fromCharCode(0x00f9);
						break;
					case 'uacute':
						ch = String.fromCharCode(0x00fa);
						break;
					case 'ucirc':
						ch = String.fromCharCode(0x00fb);
						break;
					case 'uuml':
						ch = String.fromCharCode(0x00fc);
						break;
					case 'yacute':
						ch = String.fromCharCode(0x00fd);
						break;
					case 'thorn':
						ch = String.fromCharCode(0x00fe);
						break;
					case 'yuml':
						ch = String.fromCharCode(0x00ff);
						break;
					case 'OElig':
						ch = String.fromCharCode(0x0152);
						break;
					case 'oelig':
						ch = String.fromCharCode(0x0153);
						break;
					case 'Scaron':
						ch = String.fromCharCode(0x0160);
						break;
					case 'scaron':
						ch = String.fromCharCode(0x0161);
						break;
					case 'Yuml':
						ch = String.fromCharCode(0x0178);
						break;
					case 'fnof':
						ch = String.fromCharCode(0x0192);
						break;
					case 'circ':
						ch = String.fromCharCode(0x02c6);
						break;
					case 'tilde':
						ch = String.fromCharCode(0x02dc);
						break;
					case 'Alpha':
						ch = String.fromCharCode(0x0391);
						break;
					case 'Beta':
						ch = String.fromCharCode(0x0392);
						break;
					case 'Gamma':
						ch = String.fromCharCode(0x0393);
						break;
					case 'Delta':
						ch = String.fromCharCode(0x0394);
						break;
					case 'Epsilon':
						ch = String.fromCharCode(0x0395);
						break;
					case 'Zeta':
						ch = String.fromCharCode(0x0396);
						break;
					case 'Eta':
						ch = String.fromCharCode(0x0397);
						break;
					case 'Theta':
						ch = String.fromCharCode(0x0398);
						break;
					case 'Iota':
						ch = String.fromCharCode(0x0399);
						break;
					case 'Kappa':
						ch = String.fromCharCode(0x039a);
						break;
					case 'Lambda':
						ch = String.fromCharCode(0x039b);
						break;
					case 'Mu':
						ch = String.fromCharCode(0x039c);
						break;
					case 'Nu':
						ch = String.fromCharCode(0x039d);
						break;
					case 'Xi':
						ch = String.fromCharCode(0x039e);
						break;
					case 'Omicron':
						ch = String.fromCharCode(0x039f);
						break;
					case 'Pi':
						ch = String.fromCharCode(0x03a0);
						break;
					case ' Rho ':
						ch = String.fromCharCode(0x03a1);
						break;
					case 'Sigma':
						ch = String.fromCharCode(0x03a3);
						break;
					case 'Tau':
						ch = String.fromCharCode(0x03a4);
						break;
					case 'Upsilon':
						ch = String.fromCharCode(0x03a5);
						break;
					case 'Phi':
						ch = String.fromCharCode(0x03a6);
						break;
					case 'Chi':
						ch = String.fromCharCode(0x03a7);
						break;
					case 'Psi':
						ch = String.fromCharCode(0x03a8);
						break;
					case 'Omega':
						ch = String.fromCharCode(0x03a9);
						break;
					case 'alpha':
						ch = String.fromCharCode(0x03b1);
						break;
					case 'beta':
						ch = String.fromCharCode(0x03b2);
						break;
					case 'gamma':
						ch = String.fromCharCode(0x03b3);
						break;
					case 'delta':
						ch = String.fromCharCode(0x03b4);
						break;
					case 'epsilon':
						ch = String.fromCharCode(0x03b5);
						break;
					case 'zeta':
						ch = String.fromCharCode(0x03b6);
						break;
					case 'eta':
						ch = String.fromCharCode(0x03b7);
						break;
					case 'theta':
						ch = String.fromCharCode(0x03b8);
						break;
					case 'iota':
						ch = String.fromCharCode(0x03b9);
						break;
					case 'kappa':
						ch = String.fromCharCode(0x03ba);
						break;
					case 'lambda':
						ch = String.fromCharCode(0x03bb);
						break;
					case 'mu':
						ch = String.fromCharCode(0x03bc);
						break;
					case 'nu':
						ch = String.fromCharCode(0x03bd);
						break;
					case 'xi':
						ch = String.fromCharCode(0x03be);
						break;
					case 'omicron':
						ch = String.fromCharCode(0x03bf);
						break;
					case 'pi':
						ch = String.fromCharCode(0x03c0);
						break;
					case 'rho':
						ch = String.fromCharCode(0x03c1);
						break;
					case 'sigmaf':
						ch = String.fromCharCode(0x03c2);
						break;
					case 'sigma':
						ch = String.fromCharCode(0x03c3);
						break;
					case 'tau':
						ch = String.fromCharCode(0x03c4);
						break;
					case 'upsilon':
						ch = String.fromCharCode(0x03c5);
						break;
					case 'phi':
						ch = String.fromCharCode(0x03c6);
						break;
					case 'chi':
						ch = String.fromCharCode(0x03c7);
						break;
					case 'psi':
						ch = String.fromCharCode(0x03c8);
						break;
					case 'omega':
						ch = String.fromCharCode(0x03c9);
						break;
					case 'thetasym':
						ch = String.fromCharCode(0x03d1);
						break;
					case 'upsih':
						ch = String.fromCharCode(0x03d2);
						break;
					case 'piv':
						ch = String.fromCharCode(0x03d6);
						break;
					case 'ensp':
						ch = String.fromCharCode(0x2002);
						break;
					case 'emsp':
						ch = String.fromCharCode(0x2003);
						break;
					case 'thinsp':
						ch = String.fromCharCode(0x2009);
						break;
					case 'zwnj':
						ch = String.fromCharCode(0x200c);
						break;
					case 'zwj':
						ch = String.fromCharCode(0x200d);
						break;
					case 'lrm':
						ch = String.fromCharCode(0x200e);
						break;
					case 'rlm':
						ch = String.fromCharCode(0x200f);
						break;
					case 'ndash':
						ch = String.fromCharCode(0x2013);
						break;
					case 'mdash':
						ch = String.fromCharCode(0x2014);
						break;
					case 'lsquo':
						ch = String.fromCharCode(0x2018);
						break;
					case 'rsquo':
						ch = String.fromCharCode(0x2019);
						break;
					case 'sbquo':
						ch = String.fromCharCode(0x201a);
						break;
					case 'ldquo':
						ch = String.fromCharCode(0x201c);
						break;
					case 'rdquo':
						ch = String.fromCharCode(0x201d);
						break;
					case 'bdquo':
						ch = String.fromCharCode(0x201e);
						break;
					case 'dagger':
						ch = String.fromCharCode(0x2020);
						break;
					case 'Dagger':
						ch = String.fromCharCode(0x2021);
						break;
					case 'bull':
						ch = String.fromCharCode(0x2022);
						break;
					case 'hellip':
						ch = String.fromCharCode(0x2026);
						break;
					case 'permil':
						ch = String.fromCharCode(0x2030);
						break;
					case 'prime':
						ch = String.fromCharCode(0x2032);
						break;
					case 'Prime':
						ch = String.fromCharCode(0x2033);
						break;
					case 'lsaquo':
						ch = String.fromCharCode(0x2039);
						break;
					case 'rsaquo':
						ch = String.fromCharCode(0x203a);
						break;
					case 'oline':
						ch = String.fromCharCode(0x203e);
						break;
					case 'frasl':
						ch = String.fromCharCode(0x2044);
						break;
					case 'euro':
						ch = String.fromCharCode(0x20ac);
						break;
					case 'image':
						ch = String.fromCharCode(0x2111);
						break;
					case 'weierp':
						ch = String.fromCharCode(0x2118);
						break;
					case 'real':
						ch = String.fromCharCode(0x211c);
						break;
					case 'trade':
						ch = String.fromCharCode(0x2122);
						break;
					case 'alefsym':
						ch = String.fromCharCode(0x2135);
						break;
					case 'larr':
						ch = String.fromCharCode(0x2190);
						break;
					case 'uarr':
						ch = String.fromCharCode(0x2191);
						break;
					case 'rarr':
						ch = String.fromCharCode(0x2192);
						break;
					case 'darr':
						ch = String.fromCharCode(0x2193);
						break;
					case 'harr':
						ch = String.fromCharCode(0x2194);
						break;
					case 'crarr':
						ch = String.fromCharCode(0x21b5);
						break;
					case 'lArr':
						ch = String.fromCharCode(0x21d0);
						break;
					case 'uArr':
						ch = String.fromCharCode(0x21d1);
						break;
					case 'rArr':
						ch = String.fromCharCode(0x21d2);
						break;
					case 'dArr':
						ch = String.fromCharCode(0x21d3);
						break;
					case 'hArr':
						ch = String.fromCharCode(0x21d4);
						break;
					case 'forall':
						ch = String.fromCharCode(0x2200);
						break;
					case 'part':
						ch = String.fromCharCode(0x2202);
						break;
					case 'exist':
						ch = String.fromCharCode(0x2203);
						break;
					case 'empty':
						ch = String.fromCharCode(0x2205);
						break;
					case 'nabla':
						ch = String.fromCharCode(0x2207);
						break;
					case 'isin':
						ch = String.fromCharCode(0x2208);
						break;
					case 'notin':
						ch = String.fromCharCode(0x2209);
						break;
					case 'ni':
						ch = String.fromCharCode(0x220b);
						break;
					case 'prod':
						ch = String.fromCharCode(0x220f);
						break;
					case 'sum':
						ch = String.fromCharCode(0x2211);
						break;
					case 'minus':
						ch = String.fromCharCode(0x2212);
						break;
					case 'lowast':
						ch = String.fromCharCode(0x2217);
						break;
					case 'radic':
						ch = String.fromCharCode(0x221a);
						break;
					case 'prop':
						ch = String.fromCharCode(0x221d);
						break;
					case 'infin':
						ch = String.fromCharCode(0x221e);
						break;
					case 'ang':
						ch = String.fromCharCode(0x2220);
						break;
					case 'and':
						ch = String.fromCharCode(0x2227);
						break;
					case 'or':
						ch = String.fromCharCode(0x2228);
						break;
					case 'cap':
						ch = String.fromCharCode(0x2229);
						break;
					case 'cup':
						ch = String.fromCharCode(0x222a);
						break;
					case 'int':
						ch = String.fromCharCode(0x222b);
						break;
					case 'there4':
						ch = String.fromCharCode(0x2234);
						break;
					case 'sim':
						ch = String.fromCharCode(0x223c);
						break;
					case 'cong':
						ch = String.fromCharCode(0x2245);
						break;
					case 'asymp':
						ch = String.fromCharCode(0x2248);
						break;
					case 'ne':
						ch = String.fromCharCode(0x2260);
						break;
					case 'equiv':
						ch = String.fromCharCode(0x2261);
						break;
					case 'le':
						ch = String.fromCharCode(0x2264);
						break;
					case 'ge':
						ch = String.fromCharCode(0x2265);
						break;
					case 'sub':
						ch = String.fromCharCode(0x2282);
						break;
					case 'sup':
						ch = String.fromCharCode(0x2283);
						break;
					case 'nsub':
						ch = String.fromCharCode(0x2284);
						break;
					case 'sube':
						ch = String.fromCharCode(0x2286);
						break;
					case 'supe':
						ch = String.fromCharCode(0x2287);
						break;
					case 'oplus':
						ch = String.fromCharCode(0x2295);
						break;
					case 'otimes':
						ch = String.fromCharCode(0x2297);
						break;
					case 'perp':
						ch = String.fromCharCode(0x22a5);
						break;
					case 'sdot':
						ch = String.fromCharCode(0x22c5);
						break;
					case 'lceil':
						ch = String.fromCharCode(0x2308);
						break;
					case 'rceil':
						ch = String.fromCharCode(0x2309);
						break;
					case 'lfloor':
						ch = String.fromCharCode(0x230a);
						break;
					case 'rfloor':
						ch = String.fromCharCode(0x230b);
						break;
					case 'lang':
						ch = String.fromCharCode(0x2329);
						break;
					case 'rang':
						ch = String.fromCharCode(0x232a);
						break;
					case 'loz':
						ch = String.fromCharCode(0x25ca);
						break;
					case 'spades':
						ch = String.fromCharCode(0x2660);
						break;
					case 'clubs':
						ch = String.fromCharCode(0x2663);
						break;
					case 'hearts':
						ch = String.fromCharCode(0x2665);
						break;
					case 'diams':
						ch = String.fromCharCode(0x2666);
						break;
					default:
						ch = '';
						break;
					}
				}
				i = semicolonIndex;
			}
		}
		out += ch;
	}
	return out;
}

// fin decode appel REST


/**
 * Ensemble des fonctions de validation.
 * Ces fontions compl\u00e8te les validateurs propos\u00e9es par le plugin Jquery validation avec les
 * fonction de validation \u00e9quivalents aux annotations JSR303 standard et propres \u00e0 ICDC.
 * Les fonctions doivent \u00eatre appel\u00e9es au niveau de l'attribut "data-rule" comme dans
 * les exemples suivants.
 *
 * Exemple d'utilisation de la fonction "codePostal" qui ne prend aucun param\u00e8tre, dans une
 * JSP utilisant le taglib form de Spring :
 *
 * &lt;form:input data-rule="{codePostal:true}" path="" /&gt;
 *
 *
 * Exemple d'utilisation de la fonction "size" qui prend deux param\u00e8tres min et max dans une
 * JSP utilisant le taglib form de Spring :
 *
 * &lt;form:input data-rule="{size:[11, 25]}" path="" /&gt;
 *
 */
$(function() {

	// Declaration des validateurs
	$
			.extend($.validator
					.addMethod(
							"numeroAffaire",
							function(value, element) {
								if (value == null) {
									return true;
								}
								if (value == value
										.match('[A|F]{1}[A-Za-z0-9]{0,13}')) {
									return true;
								}
								return null;
							},
							message = "doit commencer par A ou F suivi de 0 \u00e0 13 caract\u00e8res alphanum\u00e9riques"));

	$.extend($.validator.addMethod("montant", function(value, element) {
		if (value == null) {
			return true;
		}
		if (!isNaN(value) && value != 0) {
			return true;
		}
		return null;
	}, message = "doit \u00eatre un nombre"));

	$.extend($.validator.addMethod("libelle", function(value, element) {
		if (value != null && $.trim(value).length > 0) {
			return true;
		}
		return null;
	}, message = "doit \u00eatre renseign\u00e9"));

	$.extend($.validator.addMethod("date", function(value, element, param) {
		if (value == null) {
			return true;
		}
		var format = param[0];
		if (format == null || $.trim(format).length == 0) {
			format = 'dd/mm/yy';
		}
		try {
			$.datepicker.parseDate(format, value);
			return true;
		} catch (err) {
		}
		return null;
	}, message = "doit \u00eatre une date valide"));

	$
			.extend($.validator
					.addMethod(
							"compteCDC",
							function(value, element) {
								if (value == null) {
									return true;
								}
								if (value == value
										.match('^[0-9]{10}[A-Za-z]{1}')) {
									return true;
								}
								return null;
							},
							message = "seuls 10 chiffres suivi d'un caract\u00e8re alphab\u00e9tique sont accept\u00e9s"));

	$.extend($.validator.addMethod("codeBic", function(value, element) {
		if (value == null) {
			return true;
		}
		if (value == value.match('[0-9A-Za-z]{8,11}')) {
			return true;
		}
		return null;
	}, message = "le code bic doit \u00eatre entre 8 et 11 caract\u00e8res"));

	$.extend($.validator.addMethod("codePostal", function(value, element) {
		if (value != null && value == value.match('[0-9]{5}')) {
			return true;
		}
		return null;
	}, message = "le code postal doit \u00eatre un num\u00e9ro compos\u00e9 de 5 digits"));

	$.extend($.validator.addMethod("pattern", function(value, element, param) {
		var regx = param[0];
		if (value == null || regx == null) {
			return true;
		}
		if (value == value.match(regx)) {
			return true;
		}
		return null;
	}, message = "doit correspondre \u00e0 l'expression {0}"));

	$
			.extend($.validator
					.addMethod(
							"size",
							function(value, element, param) {
								if (value == null || param == null) {
									return true;
								}
								var min = param[0];
								var max;
								if (param.length > 1) {
									max = param[1];
								}
								if (min == null || $.trim(min).length == 0) {
									min = 0;
								}
								if (max == null || $.trim(max).length == 0) {
									max = 9007199254740992;
								}
								var length = $.isArray(value) ? value.length
										: $.trim(value).length;
								if (length >= min && length <= max) {
									return true;
								}
								return null;
							},
							message = "doit &ecirc;tre compos&eacute; de minimum {0} et au maxumum {1} caract&eagrave;res "));

	$.extend($.validator.addMethod("nullValue", function(value, element) {
		if (value == null || value.length == 0) {
			return true;
		}
		return null;
	}, message = "doit &ecirc;tre null"));

	$.extend($.validator.addMethod("notNull", function(value, element) {
		if (value != null && value.length > 0) {
			return true;
		}
		return null;
	}, message = "doit &ecirc;tre non null"));

	$.extend($.validator.addMethod("past", function(value, element, param) {
		var format = param[0];
		if (format == null || $.trim(format).length == 0) {
			format = 'dd/mm/yy';
		}
		if (value == null) {
			return true;
		}
		var valuedate;
		try {
			valuedate = $.datepicker.parseDate(format, value);
		} catch (err) {
			return null;
		}
		if (valuedate.getTime() < $.now()) {
			return true;
		}
		return null;
	}, message = "doit &ecirc;tre une date dans le pass&eacute;"));

	$.extend($.validator.addMethod("future", function(value, element, param) {
		var format = param[0];
		if (format == null || $.trim(format).length == 0) {
			format = 'dd/mm/yy';
		}
		if (value == null) {
			return true;
		}
		var valuedate;
		try {
			valuedate = $.datepicker.parseDate(format, value);
		} catch (err) {
			return null;
		}
		if (valuedate.getTime() > $.now()) {
			return true;
		}
		return null;
	}, message = "doit &ecirc;tre une date dans le futur"));

	$
			.extend($.validator
					.addMethod(
							"digitsclient",
							function(value, element, param) {
								var integer = param[0];
								var fraction = param[1];
								if (value == null) {
									return true;
								}
								value = $.trim(value);
								if (!value.match(/^[0-9]*([,.][0-9]*)?$/)) {
									return false;
								}
								if (integer == null) {
									return true;
								}
								var sep = ",";
								if (value.indexOf('.') != -1) {
									sep = ".";
								}
								if (value.indexOf(sep) != -1) {
									if (value.substring(0, value.indexOf(sep)).length <= integer) {
										if (fraction != null
												&& value.substring(value
														.indexOf(sep) + 1,
														value.length).length <= fraction) {
											return true;
										}
									}
								} else if (value.length <= integer) {
									return true;
								}
								return null;
							},
							message = "doit avoir au maximum {0} digits entier et {1} fractionnaire"));

	$.extend($.validator.addMethod("assertTrue", function(value, element) {
		if (value == null || $.trim(value) == 'true'
				|| $.trim(value).length == 0) {
			return true;
		}
		return null;
	}, message = "doit \u00eatre vrai"));

	$.extend($.validator.addMethod("assertFalse", function(value, element) {
		if (value == null || $.trim(value) == 'false'
				|| $.trim(value).length == 0) {
			return true;
		}
		return null;
	}, message = "doit \u00eatre faux"));

	$.extend($.validator.addMethod("notEmpty", function(value, element) {
		if (value != null) {
			if (value.length > 0) {
				return true;
			}
		}
		return null;
	}, message = "doit contenir au moins un caract&egrave;res"));

	$
			.extend($.validator
					.addMethod(
							"montantParse",
							function(value, element) {
								var MONTANT_REGEX = /^[0-9]{0,12}([,.][0-9]{1,2})?$/;
								if (value != null) {
									value = $.trim(value);
									if (value.match(MONTANT_REGEX)) {
										return true;
									}
								}
								return null;
							},
							message = "Champ num&eacute;rique de 15 caract&egrave;res maximum, dont 2 caract&egrave;res apr&egrave;s la virgule"));

	$
			.extend($.validator
					.addMethod(
							"strictementPositif",
							function(value, element) {
								if (value != null) {
									value = value.replace(/\,/g, '.');
									if (!isNaN(value) & (parseFloat(value) > 0))
										return true;
								}
								return null;
							},
							message = "Champ num&eacute;rique de 15 caract&egrave;res maximum, dont 2 caract&egrave;res apr&egrave;s la virgule"));

	//Personnalisation des messages d'erreur pour la validation
	$.extend($.validator.messages, {
		required: "La saisie de ce champ est requise.",
		remote: 'needs to get fixed',
		email: 'L\'adresse email saisie est invalide.',
		url: 'L\'url saisie est invalide.',
		date: 'La date saisie est invalide.',
		dateISO: 'Le format de la date saisie est invalide (ISO)',
		number: 'La valeur de ce champs n\'est pas un nombre valide.',
		digits: 'Ce champs ne doit comporter que des chiffres.',
		creditcard: 'is not a valid credit card number',
		equalTo: 'is not the same value again',
		accept: 'is not a value with a valid extension',
		maxlength: jQuery.validator.format('Ce champs doit \u00eatre compos\u00e9 de {0} caract\u00e8res maximum.'),
		minlength: jQuery.validator.format('Ce champs doit comporter au moins {0} caract\u00e8res.'),
		rangelength: jQuery.validator.format('Ce champs doit comporter entre {0} et {1} caract\u00e8res.'),
		range: jQuery.validator.format('La valeur de ce champs doit \u00eatre comprise entre {0} et {1}'),
		max: jQuery.validator.format('La valeur de ce champs doit \u00eatre inf\u00e9rieure ou \u00e9gale \u00e0 {0}'),
		min: jQuery.validator.format('La valeur de ce champs doit \u00eatre sup\u00e9rieures \u00e0 {0}'),
		numeroAffaire : 'doit commencer par A ou F suivi de 0 \u00e0 13 caract\u00e8res alphanum\u00e9riques',
		montant : 'doit \u00eatre un nombre',
		libelle : 'doit \u00eatre renseign\u00e9',
		date : 'doit \u00eatre une date valide',
		compteCDC : 'seuls 10 chiffres suivi d\'un caract\u00e8re alphab\u00e9tique sont accept\u00e9s',
		codePostal : 'le code postal doit \u00eatre compos\u00e9 de 5 chiffres',
		codeBic : 'le code bic doit \u00eatre entre 8 et 11 caract\u00e8res',
		pattern : jQuery.validator.format('doit correspondre \u00e0 l\'expression {0}'),
		size : jQuery.validator.format('doit \u00eatre compos\u00e9 de {0} \u00e0 {1} caract\u00e8res'),
		nullValue : 'doit \u00eatre null',
		notNull : 'doit \u00eatre non null',
		past : 'doit &\u00eatre une date dans le pass\u00e9;',
		future : 'doit \u00eatre une date dans le futur',
		digitsclient : jQuery.validator.format('doit avoir au maximum {0} digits entier et {1} fractionnaire'),
		assertTrue : 'doit \u00eatre vrai (true)',
		assertFalse : 'doit \u00eatre faux (false)',
		notEmpty : 'doit contenir au moins un caract\u00e8res',
		montantParse : 'doit \u00eatre compos\u00e9 au maximum de 12 chiffres avant la virgule et de 2 chiffes apr\u00e8s la virgule',
		strictementPositif : 'doit \u00eatre un nombre strictement positif',
	});

});
