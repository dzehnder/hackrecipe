document.addEventListener('DOMContentLoaded', () => {

    const search = instantsearch({
        appId: 'ZV6V83N86C',
        apiKey: 'dcb2605958712d4d75acf4b30c6896d3',
        indexName: 'recipes',
    });
      
    search.addWidget({
        init: function(opts) {
            const helper = opts.helper;
            const searchForRecipesElement = document.querySelector('#search-box');
            const whatsInTheFridgeElement = document.querySelector('#leftover-button');
            const searchResultElement = document.querySelector("#bon-app-search-result");

            if (whatsInTheFridgeElement) {
                whatsInTheFridgeElement.addEventListener('click', function(e) {
                    helper.setQuery('pepper kosher chicken').search();
                    searchResultElement.style.display = "block";
                });
            }
            if (searchForRecipesElement) {
                searchForRecipesElement.addEventListener('input', function(e) {
                    helper.setQuery(e.currentTarget.value).search();
                    if (searchForRecipesElement.value != '') {
                        searchResultElement.style.display = "block";
                    } else {
                        searchResultElement.style.display = "none";
                    }
                });
            }
        }
    });
    
    search.addWidget({
        render: function(opts) {
            const results = opts.results;
            // read the hits from the results and transform them into HTML.
            document.querySelector('#bon-app-search-result').innerHTML = results.hits.map(function(h) {
                var mystring = '<div id="recipe-proposal"><p>';
                mystring += '<h3>' + h._highlightResult.name.value + '</h3>';
                
                
                
                if(h.imageUrl) {      
                    mystring += '<img src="' + h.imageUrl + '" /></h3>';
                    mystring += '<br />';      
                }      



                var instock = [];
                for (var index = 0; index < h._highlightResult.ingredients.length; index++) {
                    if(h._highlightResult.ingredients[index].matchedWords != "none") {
                            instock.push(h._highlightResult.ingredients[index].value);        
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
                    if(h._highlightResult.ingredients[index].matchedWords == "none") {
                            tobuy.push(h._highlightResult.ingredients[index].value);        
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
                
                mystring += '</p></div><hr/>';
                
                return mystring;
            }).join('');
        }
    });

    search.start();

});


