import csv

def main():
    dataset1 = [None] * 2226383
    c = 0

    # Update the path for where the dataset is stored.
    file = "/Users/lyssa./Downloads/S24/softwareEngineering/realtor-data.zip2.csv"

    try:
        with open(file, mode='r') as file:
            reader = csv.reader(file)

            for line in reader:
                current_line = f"{line[2]}, {line[3]}, {line[4]}, {line[5]}, {line[9]}"
                dataset1[c] = current_line
                c += 1

    except Exception as e:
        print(e)

    for i in range(635):
        print(dataset1[i])

if __name__ == "__main__":
    main()
