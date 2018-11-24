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
                mystring += '<u>Ingredients:</u><ul>';
                
                for (var index = 0; index < h._highlightResult.ingredients.length; index++) {
                        mystring += '<li>' + h._highlightResult.ingredients[index].text.value + '</li>';
                        }

                mystring += '</ul>';
                mystring += '<a href="' + h.recipeUrl + '">' + h.recipeUrl + '</a>';
                // mystring += '<br/><i>' + JSON.stringify(h) + '</i>';
                mystring += '</p><hr/>';
                
                return mystring;
            }).join('');
        }
    });

    search.start();

});


