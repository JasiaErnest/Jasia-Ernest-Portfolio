import sys

def get_last_char(s):
    return s[-1]

def main():
    print("Enter 6 strings:")
    strings = [input().strip() for _ in range(6)]
    
    # Sort strings based on the last character
    strings.sort(key=get_last_char)
    
    # Display the sorted strings in the required format
    print("\nEnter 6 strings:", " ".join(strings))
    print("\nSorted strings:")
    print(" ".join(strings))

if __name__ == "__main__":
    main()
