// INITIAL FUNCTIONS (called at the bottom of this script)

/* attaches javascript to all buttons */
function hookButtons()
{
    document.getElementById("JSONGenerator").onclick = generateJSON;

    var button_descriptions = [["ColumnGenerator", addColumn],
                               ["ColumnDropper", dropColumn],
                               ["DailyProdRoyaltyRowGenerator", addDailyProdRoyaltyRow],
                               ["DailyProdRoyaltyRowDropper", removeLastDailyProdRoyaltyRow],
                               ["CumulativeProdRoyaltyRowGenerator", addCumulativeProdRoyaltyRow],
                               ["CumulativeProdRoyaltyRowDropper", removeLastCumulativeProdRoyaltyRow],
                               ["ProdShareRowGenerator", addProdShareRow],
                               ["ProdShareRowDropper", removeLastProdShareRow]
                              ];
    for (var desc_index = 0; desc_index < button_descriptions.length; ++desc_index)
    {
        var description = button_descriptions[desc_index];
        var button_name = description[0];
        var button_callback = description[1];
        var buttons = document.getElementsByName(button_name);

        for (var index = 0; index < buttons.length; ++index)
        {
            buttons.item(index).onclick = button_callback;
        }
    }
}

/* creates initial rows in first two tables, tranche tables do not
 * need it. */
function createInitialRows()
{
    createInitialRowsInYearlyTable();
    createInitialRowsInSingleTable();
}

// BUTTON FUNCTIONS

function generateJSON()
{
    var values = [];
    var inputRowIds = allInputRows();
    var trancheDescs = trancheJSONDescriptions();

    values.push(yearsToJSON(columns(document.getElementById("year"))));
    for (var index = 0; index < inputRowIds.length; ++index)
    {
        var row = document.getElementById(inputRowIds[index]);

        values.push(inputsToJSON(columns(row)));
    }
    for (var index = 0; index < trancheDescs.length; ++index)
    {
        var desc = trancheDescs[index];

        values.push(trancheJSON(desc[0], desc[1]));
    }

    var final_str = "{\n  " + values.join(",\n  ") + "\n}";

    document.getElementById("JSON").innerHTML = final_str;
}

function addColumn()
{
    var inputRows = allMultiInputRows();

    addYearColumn();
    for (var index = 0; index < inputRows.length; ++index)
    {
        addTextInputColumn(inputRows[index]);
    }
}

function dropColumn()
{
    var year_row = document.getElementById("year");

    if (columnCount(year_row) > 1)
    {
        var all_rows = allMultiRows();

        for (var index = 0; index < all_rows.length; ++index)
        {
            var row = document.getElementById(all_rows[index]);
            row.removeChild(row.lastChild);
        }
    }
}

function addDailyProdRoyaltyRow()
{
    addTrancheRow("DailyProdRoyaltyTable", 3);
}

function removeLastDailyProdRoyaltyRow()
{
    removeLastTrancheRow("DailyProdRoyaltyTable");
}

function addCumulativeProdRoyaltyRow()
{
    addTrancheRow("CumulativeProdRoyaltyTable", 3);
}

function removeLastCumulativeProdRoyaltyRow()
{
    removeLastTrancheRow("CumulativeProdRoyaltyTable");
}

function addProdShareRow()
{
    addTrancheRow("ProdShareTable", 4);
}

function removeLastProdShareRow()
{
    removeLastTrancheRow("ProdShareTable");
}

// JSON STRING GENERATORS

function yearsToJSON(year_columns)
{
    var values = [];

    for (var index = 1; index < year_columns.length; ++index)
    {
        values.push(year_columns[index].innerHTML.trim());
    }

    return getJSONNameAndTable(year_columns, values);
}

function inputsToJSON(input_columns)
{
    return getJSONNameAndTable(input_columns, inputsValues(input_columns));
}

function getJSONNameAndTable(columns, values)
{
    return columns[0].innerHTML.trim() + ": " + getJSONSingleLineTable(values);
}

function trancheJSON(table_id, name)
{
    var table = document.getElementById(table_id);
    var all_rows = rows(table);
    var value_strings = [];

    for (var index = 1; index < all_rows.length; ++index)
    {
        var input_columns = columns(all_rows[index]);
        var values = inputsValues(input_columns);

        value_strings.push("  " + getJSONSingleLineTable(values));
    }

    return name + ": [\n  " + value_strings.join(",\n  ") + "\n  ]";
}

function getJSONSingleLineTable(values)
{
    return "[ " + values.join(", ") + " ]";
}

// OTHER

/* Returns an array of values taken from input columns. Paramter
 * "input_columns" is an array of TD elements possibly containing an
 * INPUT child.
 */
function inputsValues(input_columns)
{
    var values = [];

    for (var index = 1; index < input_columns.length; ++index)
    {
        var input = getInputChild(input_columns[index])

        if (input == null)
        {
            continue;
        }
        if (input.type == 'text')
        {
            values.push(input.value);
        }
        else if (input.type == 'checkbox')
        {
            values.push(input.checked);
        }
    }

    return values;
}

function addYearColumn()
{
    var year_row = document.getElementById("year");
    var new_year = columnCount(year_row);
    var year_column_text = document.createTextNode(new_year);
    var year_column = createColumnWith(year_column_text);

    year_row.appendChild(year_column);
}

function addTextInputColumn(rowId)
{
    var row = document.getElementById(rowId);

    addInputColumnToRow(row);
}

function createColumnWith(child)
{
    var column = document.createElement("td");

    column.appendChild(child);

    return column;
}

function getInputChild(column)
{
    return getFirstChild(column, "INPUT");
}

function columnCount(row)
{
    return columns(row).length;
}

function columns(row)
{
    return getAllChildren(row, "TD");
}

function createInitialRowsInYearlyTable()
{
    var table = document.getElementById("YearlyTable");
    var row_ids = allMultiRows();

    for (var index = 0; index < row_ids.length; ++index)
    {
        var row = createHeaderRowCell(row_ids[index]);

        table.appendChild(row);
    }
}

function createInitialRowsInSingleTable()
{
    var table = document.getElementById("SingleValuesTable");
    var row_descs = [['text', allSingleTextRows()],
                     ['checkbox', allSingleBoolRows()]
                    ];
    var text_row_ids = allSingleTextRows();
    var bool_row_ids = allSingleBoolRows();

    for (var desc_index = 0; desc_index < row_descs.length; ++desc_index)
    {
        var row_desc = row_descs[desc_index];
        var input_type = row_desc[0];
        var row_ids = row_desc[1];

        for (var index = 0; index < row_ids.length; ++index)
        {
            var row = createHeaderRowCell(row_ids[index]);

            addInputColumnToRow(row, input_type);
            table.appendChild(row);
        }
    }
}

function createHeaderRowCell(row_id)
{
    var row = document.createElement("tr");
    var text = document.createTextNode(row_id);
    var column = createColumnWith(text);

    row.appendChild(column);
    row.setAttribute("id", row_id);

    return row;
}

function debug(str)
{
    var dbg = document.getElementById("DEBUG");

    dbg.innerHTML += str + "\n";
}

/* Returns array of ids to rows with multiple columns. */
function allMultiRows()
{
    var rows = allMultiInputRows();

    rows.unshift("year");

    return rows;
}

/* Returns an array of ids to rows with input columns (either multiple
 * or singular). */
function allInputRows()
{
    return allMultiInputRows().concat(allSingleInputRows());
}

/* Returns array of ids to rows with multiple input columns. */
function allMultiInputRows()
{
    return ["price",
            "production",
            "costRecoveryCeiling",
            "capex"];
}

/* Returns an array of ids to rows with one input column. */
function allSingleInputRows()
{
    return allSingleTextRows().concat(allSingleBoolRows());
}

/* Returns an array of ids to rows with one text input column. */
function allSingleTextRows()
{
    return ["opexPerBarrel",
            "corporateIncomeTax",
            "inflation",
            "exploration",
            "acreage",
            "rentalPerKm",
            "flatRoyaltyRate",
            "stateParticipation"];
}

/* Returns an array of ids to rows with one checkbox input column. */
function allSingleBoolRows()
{
    return ["rFactor",
            "inflationPrice",
            "inflationOpex"];
}

/* Returns a 2D array, where each 2 elements row describes a tranche -
 * first element is tranche table id and second element is name of
 * generated JSON array. */
function trancheJSONDescriptions()
{
    return [["DailyProdRoyaltyTable", "dailyProductionRoyaltyTranche"],
            ["CumulativeProdRoyaltyTable", "cumulativeProductionRoyaltyTranche"],
            ["ProdShareTable", "productionSharingTranche"]
           ];
}

function addTrancheRow(table_id, input_columns_count)
{
    var table = document.getElementById(table_id);
    var tranche_no = rows(table).length;
    var new_row = document.createElement("tr");
    var text = document.createTextNode(tranche_no);
    var first_column = createColumnWith(text);

    new_row.appendChild(first_column);
    for (var index = 0; index < input_columns_count; ++index)
    {
        addInputColumnToRow(new_row);
    }
    tableBody(table).appendChild(new_row);
}

function addInputColumnToRow(row, type)
{
    /* Fancy way of making "type" an optional parameter. */
    var input_type = (typeof type == "undefined") ? "text" : type;
    var input = createInput(input_type);
    var column = createColumnWith(input);

    row.appendChild(column);

    return column;
}

function createInput(type)
{
    var input = document.createElement("input");

    input.setAttribute("type", type);

    return input;
}

function removeLastTrancheRow(table_id)
{
    var table = document.getElementById(table_id);

    if (rows(table).length > 1)
    {
        var body = tableBody(table);

        body.removeChild(body.lastChild);
    }
}

function rows(table)
{
    return getAllChildren(tableBody(table), "TR");
}

function tableBody(table)
{
    return getFirstChild(table, "TBODY");
}

function getAllChildren(element, tagname)
{
    var nodes = element.childNodes;
    var all_rows = [];

    for (var index = 0; index < nodes.length; ++index)
    {
        var child = nodes.item(index);

        if (child != null && child.tagName == tagname)
        {
            all_rows.push(child);
        }
    }

    return all_rows;
}

function getFirstChild(element, tagname)
{
    var nodes = element.childNodes;

    for (var index = 0; index < nodes.length; ++index)
    {
        var child = nodes.item(index);

        if (child != null && child.tagName == tagname)
        {
            return child;
        }
    }

    return null;
}

hookButtons();
createInitialRows();
