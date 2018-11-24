document.addEventListener('DOMContentLoaded', () => {

    const search = instantsearch({
        appId: 'ZV6V83N86C',
        apiKey: 'dcb2605958712d4d75acf4b30c6896d3',
        indexName: 'recipes',
    });
      
    search.addWidget({
        init: function(opts) {
            const helper = opts.helper;
            const input = document.querySelector('#search-box');
            input.addEventListener('input', function(e) {
                helper.setQuery(e.currentTarget.value).search();
                if (document.getElementById("search-box").value != '') {
                    document.getElementById("bon-app-search-result").style.display = "block";
                } else {
                    document.getElementById("bon-app-search-result").style.display = "none";
                }
            });
        }
    });
    
    search.addWidget({
        render: function(opts) {
            const results = opts.results;
            // read the hits from the results and transform them into HTML.
            document.querySelector('#bon-app-search-result').innerHTML = results.hits.map(function(h) {
                var mystring = '<p>';
                mystring += '<h3>' + h._highlightResult.name.value + '</h3>';
                
                
                
                if(h.imageUrl) {      
                    mystring += '<img src="' + h.imageUrl + '" />"</h3>';
                    mystring += '<br />';      
                }      



                var instock = [];
                for (var index = 0; index < h._highlightResult.ingredients.length; index++) {
                    if(h._highlightResult.ingredients[index].text.matchLevel != "none") {
                            instock.push(h._highlightResult.ingredients[index].text.value);        
                        }
                        }    
                // print out ingredients in stock if present:
                if(instock.length > 0) {      
                    mystring += '<u>In Stock:</u><ul>';  
                    for (index = 0; index < instock.length; index++) {
                    mystring += '<li>' + instock[index] + '</li>';
                    }        
                    mystring += '</ul>';
                        }

                        
                
                var tobuy = [];
                for (var index = 0; index < h._highlightResult.ingredients.length; index++) {
                    if(h._highlightResult.ingredients[index].text.matchLevel == "none") {
                            tobuy.push(h._highlightResult.ingredients[index].text.value);        
                    }
                        }    
                // print out ingredients to buy if present:      
                if(tobuy.length > 0) {      
                    mystring += '<u>To Buy:</u><ul>';  
                    for (index = 0; index < tobuy.length; index++) {
                    mystring += '<li>' + tobuy[index] + '</li>';
                    }        
                mystring += '</ul>';
                        }

                
                
                
                mystring += '<a href="' + h.recipeUrl + '">' + h.recipeUrl + '</a>';
                
                
                // print out ingredients to buy if present:      
                if(h.healthLabels.length > 0) {      
                    mystring += '<br/><br/><u>Labels:</u> ';  
                    for (index = 0; index < h._highlightResult.healthLabels.length; index++) {
                    mystring += '#' + h._highlightResult.healthLabels[index].value;
                    if(index+1 < h._highlightResult.healthLabels.length) {
                        mystring +=  ', ';
                    } else {
                        mystring +=  '.';
                    }
                    }        
                    mystring += '</ul>';
                        }
                
                // dev only:
                // mystring += '<br/><i>' + JSON.stringify(h) + '</i>';
                
                mystring += '</p><hr/>';
                
                return mystring;
            }).join('');
        }
    });

    search.start();

});


