# This script will export the DSL of 1 or more projects
#
# You should login to your Flow server before running this script
# eg ectool --server flowserver.com login "user" "passwrd"
#
# Adjust the following array as needed
projectlist=("Custom005" "Custom006" "Custom007" "Custom008" "Custom009" "Custom010" "Custom011" "Custom012" "Autosys")

# Get web server host name and at the same time check ectool is logged in to a Flow server
if ! host=$(ectool getProperty "/server/settings/webServerHost"); then
  echo "Not logged in to a Flow server"
  exit 1
fi

# Echo summary of the export operation
echo "Exporting the following ${#projectlist[*]} projects from server $host"
echo "${projectlist[*]}\n"

# Loop through the array
for i in "${projectlist[@]}"
do
  # Echo the project name we are going to export
  printf "Exporting project $i ..."
  # Export the DSL
  ectool generateDsl "/projects/$i" --suppressNulls true --suppressDefaults true  > $i.dsl
  # Show the size of the created file
  du -h $i.dsl
done
# Finished
echo "\nFinished"
